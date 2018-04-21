#!/bin/bash

# load-test pod
# kubectl run -it load-test-service --image=ubuntu bash
# apt-get -qq update && \
# apt-get install curl && \
# apt-get install nano

for (( ; ; ))
# for i in {1..500}
do
   rnd=$RANDOM
   url=http://svc-1/multiply/$rnd
   res=$(curl $url)
   echo "result = $res"
   #echo "infinite loops [ hit CTRL+C to stop]"
done