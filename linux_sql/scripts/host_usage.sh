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

#Get all cpu usage using regex command / for host_id we used a subquery to get the id from table host_info
hostname=$(hostname -f)
vmstat_mb=$(vmstat --unit M)
memory_free=$(echo "$vmstat_mb" | awk '{print $4}'| tail -n1 | xargs)
cpu_idle=$(echo "$vmstat_mb" | tail -1 | awk '{print $15}')
cpu_kernel=$(echo "$vmstat_mb" | tail -1 | awk '{print $14}')
disk_io=$(vmstat -d | tail -1 | awk '{print $10}')
disk_available=$(df -BM / | tail -1 | awk -F'[^0-9]*' '{print $4}')
timestamp=$(vmstat -t | tail -1 | awk '{print $18 " " $19}')
host_id="(SELECT id FROM host_info WHERE hostname='$hostname')";

#Insert query  to add all host_usage to the DB
insert_stmt="INSERT INTO host_usage(timestamp,host_id,memory_free,cpu_idle,cpu_kernel,disk_io,disk_available) 
	values ('$timestamp',$host_id,'$memory_free',$cpu_idle,$cpu_kernel,$disk_io,$disk_available)"

#Assign pass to env variable PGPASSWORD to skip entering password each time
export PGPASSWORD=$psql_password

#Execute the insert query 
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"

#Exit 
exit $?
