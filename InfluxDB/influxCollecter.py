from copy import deepcopy
from datetime import datetime, timedelta
import time
import pprint
from influxdb import InfluxDBClient

def get_ifdb(db, host='localhost', port = 8086, user='root',password='root'):
    # make connection
    client = InfluxDBClient(host, port, user, password, db)
    try:
        client.create_database(db)

        print('Connection Successful')
        print('=================================')
        print(' Connection Info')
        print('=================================')
        print('host :',host)
        print('port :',port)
        print('username :',user)
        print('database :',db)
    except:
        print('Connection Failed')
        pass

    return client

def my_test(ifdb):
    json_body = []
    tablename = 'my_table'
    fieldname = 'my_field'
    point={
        "measurement" : tablename,
        "tags":{
            "host":"forever_happiness",
            "country": "South Korea",
            "region":"Busan"
        },
        "fields":{
            fieldname:0
        },
        "time":None,
    }
    vals = list(range(1,11))
    for v in vals:
        # InfluxDB is based on UTC 
        # so it should be timed with KCT
        dt = datetime.now() - timedelta(hours=-9)

        np = deepcopy(point)
        np['fields'][fieldname] = v
        np['time'] = dt

        json_body.append(np)

        time.sleep(1)
    
    ifdb.write_points(json_body)
    result = ifdb.query('select * from %s'% tablename)
    pprint.pprint(result.raw)
    



def do_test():
    #Connect to Influx DB
    mydb = get_ifdb(db='myDB')

    #wirite data to mydb
    my_test(mydb)
    


if __name__ == '__main__':
    do_test()





