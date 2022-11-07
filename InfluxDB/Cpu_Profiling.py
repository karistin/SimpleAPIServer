
import math
from os import system
import re
from time import sleep

def cpu_workload(N):
    for i in range(0, N):
        cos_i = math.cos(i)
        sin_i = math.sin(i)
        sqrt_i = math.sqrt(i)

def get_cpu_time():
    cpu_info = {}
    with open('/proc/stat', 'r') as f:
        cpu_lines = []
        for line in f.readlines():
            if line.startswith('cpu'):
                cpu_lines.append(line.split(' '))    
        for cpu_line in cpu_lines:
            if '' in cpu_line:
                cpu_line.remove('') 
            cpu_id = cpu_line[0]
            cpu_line = [float(item) for item in cpu_line[1:]]
            user, nice, system, idle, iowait, irq, softirq, steal, guest, guest_nice = cpu_line

            idle_time = idle + iowait
            non_idle_time = user + nice + system + idle + irq + iowait + softirq + steal + guest + guest_nice
            total = idle_time + non_idle_time

            cpu_info.update({cpu_id: {'total' : total , 'idle' : idle_time}})
    return cpu_info

def cpu_usage():
    cpu_info = {}
    with open('/proc/stat', 'r') as f:
        cpu_lines = []
        line = f.readline()
        if line.startswith('cpu'):
            cpu_lines.append(line.split(' '))  

        if '' in cpu_lines:
                cpu_lines.remove('') 

        cpu_id = cpu_line[0]
        cpu_line = [float(item) for item in cpu_line[1:]]
        user, nice, system, idle, iowait, irq, softirq, steal, guest, guest_nice = cpu_line

        idle_time = idle + iowait
        non_idle_time = user + nice + system + idle + irq + iowait + softirq + steal + guest + guest_nice
        total = idle_time + non_idle_time
        cpu_info.update({'total': total, 'idle' : idle_time})
    return cpu_info

def get_cpu_usage_percentage():
    start = get_cpu_time()
    cpu_workload(10 ** 6)
    end = get_cpu_time()
   
    cpu_usages = {}
    for cpu in start:
        diff_total = end[cpu]['total'] - start[cpu]['total']
        diff_idle = end[cpu]['idle'] - start[cpu]['idle']
        cpu_usage_percentage = (diff_total - diff_idle) / diff_total * 100
        cpu_usages.update({cpu : cpu_usage_percentage})
    return cpu_usages


def main():
    # result = get_cpu_usage_percentage()
    # print(result)
    while True:
        start = get_cpu_time()
        result = (start['cpu']['idle']) / start['cpu']['total'] * 100
        print(result)
        sleep(1)

if __name__=='__main__':
    main()