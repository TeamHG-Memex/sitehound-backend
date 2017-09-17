#!/usr/bin/env bash

curl -XPOST "http://localhost:9200/app/users/_search" -d '{
  "query": {
    "term": {
      "email": "foo@gmail.com"
    }
  }
}'


curl -XPOST "https://memex1.csl.sri.com:80/elasticsearch/onions/_search?start=0&search_type=query_and_fetch&size=10" -d '{
  "query": {
    "term": {
      "email": "foo@gmail.com"
    }
  }
}'

curl -XPOST "https://memex1.csl.sri.com:80/elasticsearch/onions/_search%3Fstart%3D0%26search_type%3Dquery_and_fetch%0A%26size%3D10" -d '{
  "query": {
    "term": {
      "email": "foo@gmail.com"
    }
  }
}' --verbose --sslv2


curl -k -u memex:MemexHS2014 -XPOST "https://memex1.csl.sri.com:80/elasticsearch/onions/_search%3Fstart%3D0%26search_type%3Dquery_and_fetch%0A%26size%3D10" -d '{
  "query": {
    "term": {
      "email": "foo@gmail.com"
    }
  }
}' --verbose --sslv2




#uri=elasticsearch/onions/_search?start=0&search_type=query_and_fetch&size=10,method=POST



{    "from":0, "size":10,    "query": {        "filtered" : {            "query" : {                "query_string" : {					"fields" : ["title^6", "links^2", "h1^3", "h2^3", "text"],                  "query" : "(guns AND ammo)",		            "use_dis_max" : true                }            },            "filter" : {"exists" : { "field" : "url" }            }        }    }}
