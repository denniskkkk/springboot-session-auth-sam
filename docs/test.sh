#list
curl -X GET http://localhost:8080/data/list

curl -X GET http://localhost:8080/data/list/55
#create
curl localhost:8080/data/list -d '{"name":"two","address":"address"}' -H "Content-Type: application/json" -X POST
#delete
curl localhost:8080/data/list/54  -X DELETE
#update
curl http://localhost:8080/data/list/60 -d '{ "name":"xxxxtwo","address":"axxxxddress"}' -H "Content-Type: application/json" -X PUT
#update
curl http://localhost:8080/data/list/61 -d '{ "name":"xxxaaaaxtwo","address":"axxxxddress"}' -H "Content-Type: application/json" -X PUT

