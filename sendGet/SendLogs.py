import requests
import json
import time
import random
import string

url = 'http://log-dev-d1-one-controller.bonree.com/logs/otel/v1/logs'
headers = {'Content-Type':'application/json','Accept':'*/*','brkey':'77b14e38-e330-11ed-8eb2-5a18c2525e2c'}
token = '6c8e3bf6-a788-11ed-b908-0e832fe4bb04'
params = {'token':token, 'type':'client'}

print(url)
while True:
    length = 10
    characters = string.ascii_letters + string.digits
    random_string = ''.join(random.choice(characters) for i in range(length))
    data = {'resourceLogs':[{'resource':{'attributes':[{'key':'version','value':{'stringValue': random_string }},{'key':'logger.version','value':{'stringValue':'1.0.0'}},{'key':'deviceId','value':{'stringValue':'4998-A0C0-A0B32F93AEE3'}},{'key':'deviceName','value':{'stringValue':'aaa'}},{'key':'br.agent.type','value':{'stringValue':'101'}}]},'scopeLogs':[{'logRecords':[{'attributes':[{'key':'trace_id','value':{'stringValue':'a4803f17-12f7-4afc-a022-319431c2afef'}},{'key':'chz','value':{'stringValue':'chz'}},{'key':'logger.threadName','value':{'stringValue':'Thread'}}],'time_unix_nano':time.time_ns(),'severity_number':'SEVERITY_NUMBER_INFO','severity_text':'Info','body':{'string_value':'Server started, listening on ' + str(random.randint(1, 65535))}}]}]}]}
    # print(data)
    response = requests.post(url, headers=headers, data=json.dumps(data), params=params)
    # response = requests.get(url, headers=headers, data=data, params=params)
    print(response.text)
    time.sleep(1)
