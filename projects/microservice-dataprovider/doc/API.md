# 接口文档

## 日志统计接口设计

- **URL:** `/api/service/latencyCheck/${second}`
- **Method:** `GET`

### 请求参数

**second type:int**

**返回接口日志最近多少秒内的所有时间戳,最大时间不要超过300s**

### 响应

| 序号 | message         | 含义           |
|----|-----------------|--------------|
| 1  | trans_id        | 请求唯一trans_id |
| 2  | query_start_tsp | 查询开始时间戳      |
| 3  | query_end_tsp   | 查询结束时间戳      |
| 4  | req_start_tsp   | 请求开始时间戳      |
| 5  | req_end_tsp     | 请求结束时间戳      |

```json
{
  "message": [
    {
      "query_end_tsp": "2023-11-20T06:53:43Z",
      "req_end_tsp": "2023-11-20T06:53:43Z",
      "query_start_tsp": "2023-11-20T06:53:43Z",
      "trans_id": "8b063d08877111eeb29c2d36c49e5645",
      "req_start_tsp": "2023-11-20T06:53:43Z"
    },
    {
      "query_end_tsp": "2023-11-20T06:53:56Z",
      "req_end_tsp": "2023-11-20T06:53:56Z",
      "query_start_tsp": "2023-11-20T06:53:56Z",
      "trans_id": "9315d649877111eeb29c213acbe80e9d",
      "req_start_tsp": "2023-11-20T06:53:56Z"
    },
    {
      "query_end_tsp": null,
      "req_end_tsp": "2023-11-20T06:53:58Z",
      "query_start_tsp": null,
      "trans_id": "93e2c33a877111eeb29caf110845e2b1",
      "req_start_tsp": "2023-11-20T06:53:58Z"
    }
  ]
}
```

### 请求示例

shell

```shell
curl --location '127.0.0.1:8080/api/service/latencyCheck/30'
```

python

```python
import requests

url = "127.0.0.1:8080/api/service/latencyCheck/30"

payload = {}
headers = {}

response = requests.request("GET", url, headers=headers, data=payload)

print(response.text)

```

java

```java
Unirest.setTimeouts(0,0);
        HttpResponse<String> response=Unirest.get("127.0.0.1:8080/api/service/latencyCheck/30")
        .asString();

```

### 数据表结构

```sql
CREATE TABLE req_log_info
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    trans_id        VARCHAR(50),
    req_path        VARCHAR(128),
    req_start_tsp   TIMESTAMP,
    req_end_tsp     TIMESTAMP,
    query_start_tsp TIMESTAMP,
    query_end_tsp   TIMESTAMP,
    req_time        VARCHAR(50),
    info            VARCHAR(255),
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```