from abc import *
from time import sleep

class OS():
    
    CPU ={

    },
    Mem = {

    },
    Net = {

    },
    Io = {

    },

class Info():
    def __init__(self,ServerName, Ip, OsType, OsVersion, CpuCore, AgentVersion ):
        self.ServerName = ServerName
        self.Ip = Ip
        self.OsType = OsType
        self.OsVersion = OsVersion
        self.CpuCore = CpuCore
        self.AgentVersion = AgentVersion
    

    def __str__(self):
        return (" ServerName :"+self.ServerName +\
                " IP : "+ self.Ip +\
                " OsType :" + self.OsType +\
                " OsVersion : "+ self.OsVersion +\
                " CpuCore : "+ self.CpuCore+\
                " AgentVersion : "+self.AgentVersion)
#       user  nice system  idle      iowait  irq   softirq  steal  guest  guest_nice
# cpu  6913   0    12037   15717018  252     0     1972     0      0      0
class CPU():
    def __init__(self, user, nice, system, idle, iowait, irq, softirq, steal, guest, guest_nice):
        self.user = user
        self.nice = nice
        self.system = system
        self.idle = idle
        self.iowait = iowait
        self.irq = irq
        self.softirq = softirq
        self.steal = steal
        self.guest = guest
        self.guest_nice = guest_nice

def Cpu():
    last_idle = last_total = 0
    while True:
        with open('/proc/stat') as f:
            fields = [float(column) for column in f.readline.strip().split()[1:]]
        idle, total = fields[3], sum(fields)
        idle_delta, total_delta = idle - last_idle, total - last_total
        last_idle, last_total = idle, total
        utilisation = 100.0 * (1.0 - idle_delta/total_delta)
        print('%5.1f%%' %utilisation, end='\r')
        sleep(5)


if __name__=='__main__':
    ServerName = 'jennifer'
    Ip = "127.0.0.1"
    OsType = "linux"
    OsVersion = "Ubuntu 18.04.6 LTS" 
    CpuCore = '8'
    AgnetVersion = "1.0.0"
    Cpu()
    # info = Info(ServerName,Ip,OsType,OsVersion,CpuCore,AgnetVersion)
    # print(info)