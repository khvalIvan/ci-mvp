curl -X POST -H "Content-Type:application/xml" -d @seedjob_config.xml "http://localhost:8080/createItem?name=SeedJob"
# curl "http://localhost:8080/job/SeedJob/build"