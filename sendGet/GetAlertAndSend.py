# https://oneupload.bonree.com/alert/upload/event/139d9162-e9fa-4c8b-b9df-715a69b7e055
import json
import time
import requests


file_path = r'C:\Users\bonree\Desktop\cmdb.txt'
cmdb_list = []
cmdb_header_list = []



index = 0;
with open(file_path, 'r') as file:
    for line in file:
        fields = line.split()
        if index == 0:
            index = index + 1;
            cmdb_header_list.extend(fields)
        else:
            cmdb_line_dict = {}
            for i in range(len(fields)):
                header = cmdb_header_list[i]
                cmdb_line_dict[header] = fields[i];
            cmdb_list.append(cmdb_line_dict)


def getDict(obejct_str, cmdb):
    for element in cmdb:
        if obejct_str == element[cmdb_header_list[2]]:
            return element;
    return {'系统简称':'','服务器IP地址':'','所在物理机':'','数据库实例':'','数据库ip':''}

## 发送post请求
headers = {'Content-Type':'application/json'}

# 读取excel为字典， 扩列操作
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
        element_dict = eval(element)
        cmdb_info = getDict(element_dict["object"], cmdb_list)
        element_dict.update(cmdb_info)
        response = requests.post(target_url, headers=headers, data=json.dumps(element_dict))
        print(response.text)
    time.sleep(5)