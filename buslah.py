#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Mar 16 21:43:05 2021

@author: hanabilaf
"""

import sqlite3
import pandas as pd

# EXERCISE 1 

class BusLahException(Exception):
    pass

def build_database(db_file, bus_stops, bus_routes, bus_services):
    try:
        conn = sqlite3.connect(db_file)
        table_bus_stops = pd.read_csv(bus_stops)
        table_bus_stops.to_sql('bus_stops',conn,if_exists='replace',index=True)
        table_bus_services = pd.read_csv(bus_services)
        table_bus_services.to_sql('bus_services',conn,if_exists='replace',index=True)
        table_bus_routes = pd.read_csv(bus_routes)
        table_bus_routes.to_sql('bus_routes',conn,if_exists='replace',index=True)
    except:
        raise BusLahException

# EXERCISE 2
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
    

class SQLahDB(GenericDB):
    
    def __init__ (self, sqlitedb):
        super().__init__(sqlitedb)
        
    def stops_query(self):
        query = "SELECT bus_stop_code FROM bus_stops;"
        return pd.read_sql_query(query,self.conn)
    
    def line_stops_query(self,line, direction):
        query = "SELECT bus_stop_code FROM bus_routes WHERE service_number = '{}' AND direction = '{}'".format(line, direction)
        return pd.read_sql_query(query,self.conn)
    
    def most_left(self):
        query = "SELECT bus_stop_code, min(longitude) FROM bus_stops"
        res = pd.read_sql_query(query,self.conn)
        res["bus_stop_code"]= res["bus_stop_code"].astype(str)
        return res
    
    def area_line(self,lon_min,lon_max, lat_min, lat_max):
        query = "SELECT DISTINCT service_number FROM bus_stops INNER JOIN bus_routes ON bus_routes.bus_stop_code = bus_stops.bus_stop_code WHERE (longitude BETWEEN '{}' AND '{}') AND (latitude BETWEEN '{}' AND '{}')".format(lon_min, lon_max, lat_min, lat_max)
        return pd.read_sql_query(query,self.conn)

