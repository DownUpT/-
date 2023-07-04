# https://oneupload.bonree.com/alert/upload/event/139d9162-e9fa-4c8b-b9df-715a69b7e055
import json
import time
import requests


## 发送post请求
headers = {'Content-Type':'application/json'}
while True:
    ## 从客户的url获取数据
    source_url = 'https://paas.bkpaasdev2.com/o/cw_uac_saas/alarm/api/active/list/?query={%22status%22:[%22received%22,%22dispatched%22,%22abnormal%22,%22pending_execute%22,%22executing%22,%22autoorder_executing%22,%22autoexecute_executing%22,%22autoexecuting_failure%22]}'
    # 绕开https验证
    sourceReponse = requests.get(source_url, headers=headers, verify=False)
    alert_map = json.loads(sourceReponse.text)
    ## 生成one-alert所需格式
    items = alert_map["items"]
    for element in items:
        target_url = "https://oneupload.bonree.com/alert/upload/event/139d9162-e9fa-4c8b-b9df-715a69b7e055"
        response = requests.post(target_url, headers=headers, data=json.dumps(element))
        print(response.text)
    time.sleep(5)