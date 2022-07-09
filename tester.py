#!/usr/bin/env python3
# -*- coding: utf-8 -*-


## CONFIGURATION

_dbfile       = "buslah.sqlite"
_csv_routes    = "singbus/bus_routes.csv"
_csv_services  = "singbus/bus_services.csv"
_csv_stops     = "singbus/bus_stops.csv"

import os
import matplotlib.pyplot as plt
import matplotlib.image as mpimg

assert (os.path.exists(_csv_routes))
assert (os.path.exists(_csv_services))
assert (os.path.exists(_csv_stops))


class Student:
    
    points = {"Import" : 1}
    
    def __init__ (self, filename) :
        self.filename = filename
        self.score    = {}
        self.error    = {}
        self.load     = 0
        self.extract  = 0

    def add_error(self, category) :
        self.error[category] = self.error.get(category,0) + 1
    
    def add_points(self, category) :
        self.score[category] = self.score.get(category,0) + self.points.get(category,1)
        
    def set_times(self, load, extract) :
        self.load = load
        self.extract = extract
        
    def resetScore(self) :
        self.score = {}
    def getScore(self) :
        return sum(self.score.values())
    
    def to_string (self) :
        return "{} {} {} {}" . format(self.filename, self.getScore(), numpy.mean(self.load), numpy.mean(self.extract)) 
       
class TestBench:
    tests = {}
    def __init__ (self) :
        self.tests = {}
    def addTest (self, testname, testfunc) :
        self.tests[testname] = testfunc

def test_all (s) :
    
    student = Student(s)
    
    print (" ************************************************************** ")
    print ('Process import for' , student.filename)
    print (" ************************************************************** ")
    
    
    import importlib
    student.package = None
    
    try :
        student.package = importlib.import_module(student.filename)
        student.add_points("T0_Import")
        print ("Import done")
    except Exception as e:
        print ("failed test:", e)
        
        
    print (" ************************************************************** ")
    print ('Process test for' , student.filename)
    print (" ************************************************************** ")
    
    

    student.resetScore()
    print("Current score:", student.getScore())    
    for name,fun in tests.items() :
        student.resetScore()
        try :
            print (" ################ ")
            print (" ################ Start ", str(name))
            print (" ################ ")
            fun(student)
            print ("  - Finished {} without exception.".format(str(name)))
            student.add_points(name)
        except Exception as e :
            print ('  - Error failed the test {}:{}'.format(name,e))
        print("Score:", student.getScore())
    
    return student

    
def basic_test_1 (student) :
    
    
    if (os.path.exists(_dbfile)) :
        os.remove(_dbfile)
    else : 
        print("  Info: Database file not created before")
        
        
    
    print("   basic_test_1 start")
    buslah = student.package
    try :
        buslah.build_database(db_file = _dbfile, 
                       bus_routes = _csv_routes,
                       bus_services = _csv_services,
                       bus_stops = _csv_stops)
        print("   build_database done")
    except buslah.BusLahException as e:
        print("   Error, BusLahException exception raised:", e)
    except Exception as e :
        print("   Error, BaseException raised:", e)
        
        
    ### TEST 1 - CHECK THAT THE FILE IS CREATED ###
    
    if (os.path.exists(_dbfile)) :
        student.add_points("os.path.exists(_dbfile)")
    else : 
        print("Error, database file not created")
        
        

    ### TEST 2 - CHECK THAT THE TABLES ARE CREATED ###
    import sqlite3 
    import pandas as pd

    conn = sqlite3.connect(_dbfile)
    df = pd.read_sql_query("SELECT name FROM sqlite_master WHERE type='table' ORDER BY name;", conn)
    conn.close()

    if  ("name" in df):
        student.add_points("name in df")
    else : 
        print("Error, request failed, no name")
        
    if  (len(df) == 3):
        student.add_points("database len correct")
    else :
        print(df)
        print("Error, request failed, df not len 3")
        
    if  ("bus_stops" in list(df['name'])):
        student.add_points("bus_stops")
    else : 
        print("Error, request failed no bus_stops")
        
    if  ("bus_services" in list(df['name'])):
        student.add_points("bus_services")
    else : 
        print("Error, request failed no bus_services")
        
    if  ("bus_routes" in list(df['name'])):
        student.add_points("bus_routes")
    else : 
        print("Error, request failed no bus routes")



    ### TEST 3 - CHECK THAT THE DATA IS HERE ###
    import sqlite3 
    import pandas as pd

    conn = sqlite3.connect(_dbfile)
    df = pd.read_sql_query("SELECT * FROM bus_routes WHERE service_number == 10 AND direction == 1 AND distance_traveled > 31;", conn)
    conn.close()

    if(len(df) == 2):
        student.add_points("len OK")
    else :
        print(df)
        print("Error, request failed len is not 2 (index 72 and 73 expected)")
        
    if(df.sum()["sunday_last_bus"] == "00230024"):
        student.add_points("sum OK")
    else : 
        print(df["sunday_last_bus"])
        print(df.sum()["sunday_last_bus"])
        print("Error, request failed sunday_last_bus != 0516")

    ### TEST 4 - CHECK THAT ERROR MANAGEMENT IS CORRECT ###
    try :
        buslah.build_database(db_file  = "error.sqlite", 
                           bus_routes   = "Thisfiledoesnotexist",
                           bus_services = _csv_services,
                           bus_stops    = _csv_stops)
        print("Error, exception was not raised")
    except buslah.BusLahException as e:
        student.add_points("exception OK" + str(e))
    except BaseException as e :
        print("Error, BaseException raised instead of BusLahException:", e)

    
    print("basic_test_1 done")

class GenericDB :

    def __init__ (self, sqlitedb) :
        self.conn = sqlite3.connect(sqlitedb)
        
    def get_tables(self):
        return pd.read_sql_query("SELECT name FROM sqlite_master WHERE\
                                  type='table' ORDER BY name;", self.conn)
    
    def get_table_infos(self,table):
        return pd.read_sql_query("PRAGMA table_info({});".format(table), self.conn)
    
    def custom_request(self,req):
        return pd.read_sql_query(req, self.conn)



def basic_test_2 (student) :
    print("basic_test_2 start")
    buslah = student.package
    try :
        buslah.build_database(db_file = _dbfile, 
                       bus_routes = _csv_routes,
                       bus_services = _csv_services,
                       bus_stops = _csv_stops)
        print("build_database done")
    except buslah.BusLahException as e:
        print("Warning, BusLahException after build twice (no penalty)")
    except BaseException as e:
        print("Error, BaseException after build twice (should be OK or BusLahException)", e)
        
        
    
    ### TEST 1 - LOAD DATA ###
    
    try :
        sqlahdb = buslah.SQLahDB(_dbfile)
        df = sqlahdb.get_tables()
    except buslah.BusLahException :
        print("Warning, exception after buld twice")
    except BaseException as e:
        print("Error, exception failed:",e)
        
    if ("name" in df):
        student.add_points("")
    else : 
        print("Error, incorrect df: name not in df")   
        
    if (len(df) == 3):
        student.add_points("")
    else : 
        print("Error, incorrect df: len(df) != 3")  
        
    if ("bus_stops" in list(df['name'])):
        student.add_points("")
    else : 
        print("Error, incorrect df: bus_stops not in list(df['name']")  
        
    if ("bus_services" in list(df['name'])):
        student.add_points("")
    else : 
        print("Error, incorrect df: bus_services not in list(df['name']")   
        
    if ("bus_routes" in list(df['name'])):
        student.add_points("")
    else : 
        print("Error, incorrect df: bus_routes not in list(df['name']")   
    
    
    
    ### TEST 2 - TEST stops_query ###

    if("bus_stop_code" in sqlahdb.stops_query()):
        student.add_points("")
    else : 
        print("Error, incorrect df: bus_stop_code not in query")   
    
    
    if(len(sqlahdb.stops_query()) > 5000):
        student.add_points("")
    else : 
        print("Error, incorrect df: stops_query too small")   
    
    
    
    
    
    ### TEST 3 - TEST line_stops_query ###

    if("bus_stop_code" in sqlahdb.line_stops_query('96', 1)):
        student.add_points("")
    else : 
        print("Error, incorrect df: bus_stop_code not found")   
    
    
    if(len(sqlahdb.line_stops_query('96', 1)) > 10):
        student.add_points("")
    else :
        print(sqlahdb.line_stops_query('96', 1))
        print("Error, incorrect df: line_stops_query no enough")   
    
    
    
    
    
    ### TEST 4 - TEST most_left ###

    if("bus_stop_code" in sqlahdb.most_left()):
        student.add_points("")
    else : 
        print("Error, incorrect df: bus_stop_code not in most left")   
    
    
    if(len(sqlahdb.most_left()) > 0):
        student.add_points("")
    else : 
        print("Error, incorrect df: no most_left")   
    
    
    if(str(sqlahdb.most_left()["bus_stop_code"][0]) == '25751'):
        student.add_points("")
    else :
        print("sqlahdb.most_left() === ", sqlahdb.most_left())
        print("Error, incorrect df: not 25751")   
    
    
    
    
    
    ### TEST 5 - TEST area_line ###

    if('service_number' in sqlahdb.area_line(103.0, 104.0, 1.2, 1.4)):
        student.add_points("")
    else : 
        print("Error, incorrect df: no service_number")
        
    if(sum('402' == sqlahdb.area_line(103.0, 104.0, 1.2, 1.4)['service_number']) == 1):
        student.add_points("")
    else : 
        print("Error, incorrect df: no 402")   
        print(sqlahdb.area_line(103.0, 104.0, 1.2, 1.4)['service_number'])
    
    
    print("basic_test_2 done")


## This section of code is just to generate a nice diagram.

def basic_test_3 (student) :
    print("basic_test_3 start")
    buslah = student.package
    import warnings
    warnings.filterwarnings('ignore')
    from eralchemy import render_er
    render_er("sqlite:///" + _dbfile, 'buslah.png')



tests = {}    
tests["basic_test_1"] = basic_test_1
tests["basic_test_2"] = basic_test_2
tests["basic_test_3"] = basic_test_3



test_all('buslah')



