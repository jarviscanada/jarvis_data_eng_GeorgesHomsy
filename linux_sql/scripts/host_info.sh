#!/bin/bash

#Declare variables for all arguments
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5


#Checking if argument number is correct
if [ $# -ne 5 ]; then
	echo 'Wrong number of arguments'
	exit 1
fi

#Get all cpu info using regex command
hostname=$(hostname -f)
lscpu_out=`lscpu`
cpu_number=$(echo "$lscpu_out" | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$lscpu_out" | egrep "^Archi" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out" | egrep "^Model n" | awk '{$1=$2="";print $0}' | xargs)
cpu_mhz=$(echo "$lscpu_out" | egrep "\bMHz\b" | awk '{$1=$2="";print $0}' | xargs)
l2_cache=$(echo "$lscpu_out" | egrep "^L2" | awk -F'[^0-9]*' '{print $3}' | xargs)
total_mem=$(cat /proc/meminfo | grep "MemTotal" | awk '{print $2}')
timestamp=$(date --utc "+%Y-%m-%d %H:%M:%S")

#Insert query  to add all host_info to the DB
insert_stmt="INSERT INTO host_info(hostname,cpu_number,cpu_architecture,cpu_model,cpu_mhz,L2_cache,total_mem,timestamp) 
	values ('$hostname',$cpu_number,'$cpu_architecture','$cpu_model',$cpu_mhz,$l2_cache,$total_mem,'$timestamp')"

#Assign pass to env variable PGPASSWORD to skip entering password
export PGPASSWORD=$psql_password

#Execute the insert query 
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"

#Exit 
exit $?
