/* 1st query */
select cpu_number,
       id,
       total_mem
from host_info
group by cpu_number, id, total_mem
order by total_mem DESC;

/* 2nd query */
select hu.host_id,hi.hostname,
       date_trunc('hour', hu.timestamp) + date_part('minute', hu.timestamp):: int / 5 * interval '5 min' as "timestamp",
       avg((hi.total_mem-hu.memory_free)*100/hi.total_mem) as avg_used_mem_percentage
from host_usage hu
join host_info hi on hu.host_id = hi.id
group by hu.host_id,hi.hostname, date_trunc('hour', hu.timestamp) + date_part('minute', hu.timestamp):: int / 5 * interval '5 min'
order by "timestamp" ASC;

/* 3rd query */
select host_id,
       date_trunc('hour', timestamp) + date_part('minute', timestamp):: int / 5 * interval '5 min' as "5min",
       count(timestamp) as num_data_points
from host_usage
group by host_id, date_trunc('hour', timestamp) + date_part('minute', timestamp):: int / 5 * interval '5 min'
having count(timestamp) < 3

