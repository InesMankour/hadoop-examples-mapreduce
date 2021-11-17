# LAB 2 YARN MAP REDUCE

```
[ines.mankour@hadoop-edge01 ~]$ hdfs dfs -ls
Found 6 items
drwx------   - ines.mankour ines.mankour          0 2021-10-06 08:00 .Trash
drwx------   - ines.mankour ines.mankour          0 2021-10-21 15:22 .staging
-rw-r--r--   1 ines.mankour ines.mankour    3369045 2021-10-21 15:16 davinci.txt
drwxr-xr-x   - ines.mankour ines.mankour          0 2021-10-05 23:42 raw
-rw-r--r--   3 ines.mankour ines.mankour      16778 2021-11-04 14:03 trees.csv
drwxr-xr-x   - ines.mankour ines.mankour          0 2021-10-21 15:22 user

[ines.mankour@hadoop-edge01 ~]$ ls
davinci.txt  hadoop-examples-mapreduce-1.0-SNAPSHOT-jar-with-dependencies.jar  local.txt  message  secret-of-the-universe  trees.csv  wordcount

```
### 1.6.3 Run the job
### On fait un alias pour la commande yarn jar pour simplifier les commandes 


```

[ines.mankour@hadoop-edge01 ~]$ alias run_job="yarn jar hadoop-examples-mapreduce-1.0-SNAPSHOT-jar-with-dependencies.jar"
[ines.mankour@hadoop-edge01 ~]$ run_job wordcount trees.csv outputwordcount


21/11/07 18:04:31 INFO mapreduce.Job: Job job_1630864376208_5021 running in uber mode : false
21/11/07 18:04:31 INFO mapreduce.Job:  map 0% reduce 0%
21/11/07 18:04:39 INFO mapreduce.Job:  map 100% reduce 0%
21/11/07 18:04:49 INFO mapreduce.Job:  map 100% reduce 100%
21/11/07 18:04:49 INFO mapreduce.Job: Job job_1630864376208_5021 completed successfully


[ines.mankour@hadoop-edge01 ~]$ hdfs dfs -cat outputwordcount/part-r-00000

...

sempervirent;;27;Parc   1
singe;;39;Bois  1
vert;;98;Bois   1
à       13
écus;;10;Parc   1
écus;;31;Jardin 1
écus;;46;Parc   1
écus;;64;Bois   1
écus;;84;Bois   1
île     1
```
### 1.8.1 Districts containing trees
```


[ines.mankour@hadoop-edge01 ~]$ run_job distinctDistricts trees.csv districts  

[ines.mankour@hadoop-edge01 ~]$ result districts
3       1
4       1
5       2
6       1
7       3
8       5
9       1
11      1
12      29
13      2
14      3
15      1
16      36
17      1
18      1
19      6
20      3
````

### 1.8.2 Show all existing species


```
[ines.mankour@hadoop-edge01 ~]$ run_job species trees.csv species; printf "\n"; result species

[...]
21/11/17 19:41:48 INFO mapreduce.Job: The url to track the job: https://hadoop-master02.efrei.online:8090/proxy/application_1637167060931_0029/
21/11/17 19:41:48 INFO mapreduce.Job: Running job: job_1637167060931_0029
21/11/17 19:41:58 INFO mapreduce.Job: Job job_1637167060931_0029 running in uber mode : false
21/11/17 19:41:58 INFO mapreduce.Job:  map 0% reduce 0%
21/11/17 19:42:06 INFO mapreduce.Job:  map 100% reduce 0%
21/11/17 19:42:11 INFO mapreduce.Job:  map 100% reduce 100%
21/11/17 19:42:11 INFO mapreduce.Job: Job job_1637167060931_0029 completed successfully
21/11/17 19:42:11 INFO mapreduce.Job: Counters: 54
        File System Counters
                FILE: Number of bytes read=547
                FILE: Number of bytes written=527743
                FILE: Number of read operations=0
                FILE: Number of large read operations=0
                FILE: Number of write operations=0
                HDFS: Number of bytes read=16883
                HDFS: Number of bytes written=451
                HDFS: Number of read operations=8
                HDFS: Number of large read operations=0
                HDFS: Number of write operations=2
                HDFS: Number of bytes read erasure-coded=0
        Job Counters
                Launched map tasks=1
                Launched reduce tasks=1
                Data-local map tasks=1
                Total time spent by all maps in occupied slots (ms)=19839
                Total time spent by all reduces in occupied slots (ms)=10200
                Total time spent by all map tasks (ms)=6613
                Total time spent by all reduce tasks (ms)=2550
                Total vcore-milliseconds taken by all map tasks=6613
                Total vcore-milliseconds taken by all reduce tasks=2550
                Total megabyte-milliseconds taken by all map tasks=10157568
                Total megabyte-milliseconds taken by all reduce tasks=5222400
        Map-Reduce Framework
                Map input records=98
                Map output records=97
                Map output bytes=995
                Map output materialized bytes=547
                Input split bytes=105
                Combine input records=97
                Combine output records=45
                Reduce input groups=45
                Reduce shuffle bytes=547
                Reduce input records=45
                Reduce output records=45
                Spilled Records=90
                Shuffled Maps =1
                Failed Shuffles=0
                Merged Map outputs=1
                GC time elapsed (ms)=201
                CPU time spent (ms)=2510
                Physical memory (bytes) snapshot=1449553920
                Virtual memory (bytes) snapshot=7283290112
                Total committed heap usage (bytes)=1507852288
                Peak Map Physical memory (bytes)=1157955584
                Peak Map Virtual memory (bytes)=3403157504
                Peak Reduce Physical memory (bytes)=291598336
                Peak Reduce Virtual memory (bytes)=3880132608
        Shuffle Errors
                BAD_ID=0
                CONNECTION=0
                IO_ERROR=0
                WRONG_LENGTH=0
                WRONG_MAP=0
                WRONG_REDUCE=0
        File Input Format Counters
                Bytes Read=16778
        File Output Format Counters
                Bytes Written=451

araucana
atlantica
australis
baccata
bignonioides
biloba
bungeana
cappadocicum
carpinifolia
colurna
coulteri
decurrens
dioicus
distichum
excelsior
fraxinifolia
giganteum
giraldii
glutinosa
grandiflora
hippocastanum
ilex
involucrata
japonicum
kaki
libanii
monspessulanum
nigra
nigra laricio
opalus
orientalis
papyrifera
petraea
pomifera
pseudoacacia
sempervirens
serrata
stenoptera
suber
sylvatica
tomentosa
tulipifera
ulmoides
virginiana
x acerifolia

```

### 1.8.3 Number of trees by kinds
here we have the number of trees per species 


```
[ines.mankour@hadoop-edge01 ~]$ run_job speciesCount trees.csv species_count; printf "\n"; result species_count

[...]
21/11/17 19:57:25 INFO mapreduce.Job: The url to track the job: https://hadoop-master02.efrei.online:8090/proxy/application_1637167060931_0030/
21/11/17 19:57:25 INFO mapreduce.Job: Running job: job_1637167060931_0030
21/11/17 19:57:35 INFO mapreduce.Job: Job job_1637167060931_0030 running in uber mode : false
21/11/17 19:57:35 INFO mapreduce.Job:  map 0% reduce 0%
21/11/17 19:57:43 INFO mapreduce.Job:  map 100% reduce 0%
21/11/17 19:57:48 INFO mapreduce.Job:  map 100% reduce 100%
21/11/17 19:57:49 INFO mapreduce.Job: Job job_1637167060931_0030 completed successfully
21/11/17 19:57:49 INFO mapreduce.Job: Counters: 54
        File System Counters
                FILE: Number of bytes read=727
                FILE: Number of bytes written=528153
                FILE: Number of read operations=0
                FILE: Number of large read operations=0
                FILE: Number of write operations=0
                HDFS: Number of bytes read=16883
                HDFS: Number of bytes written=542
                HDFS: Number of read operations=8
                HDFS: Number of large read operations=0
                HDFS: Number of write operations=2
                HDFS: Number of bytes read erasure-coded=0
        Job Counters
                Launched map tasks=1
                Launched reduce tasks=1
                Data-local map tasks=1
                Total time spent by all maps in occupied slots (ms)=19677
                Total time spent by all reduces in occupied slots (ms)=11528
                Total time spent by all map tasks (ms)=6559
                Total time spent by all reduce tasks (ms)=2882
                Total vcore-milliseconds taken by all map tasks=6559
                Total vcore-milliseconds taken by all reduce tasks=2882
                Total megabyte-milliseconds taken by all map tasks=10074624
                Total megabyte-milliseconds taken by all reduce tasks=5902336
        Map-Reduce Framework
                Map input records=98
                Map output records=97
                Map output bytes=1383
                Map output materialized bytes=727
                Input split bytes=105
                Combine input records=97
                Combine output records=45
                Reduce input groups=45
                Reduce shuffle bytes=727
                Reduce input records=45
                Reduce output records=45
                Spilled Records=90
                Shuffled Maps =1
                Failed Shuffles=0
                Merged Map outputs=1
                GC time elapsed (ms)=191
                CPU time spent (ms)=2450
                Physical memory (bytes) snapshot=1453015040
                Virtual memory (bytes) snapshot=7284752384
                Total committed heap usage (bytes)=1505755136
                Peak Map Physical memory (bytes)=1157492736
                Peak Map Virtual memory (bytes)=3403390976
                Peak Reduce Physical memory (bytes)=295522304
                Peak Reduce Virtual memory (bytes)=3881361408
        Shuffle Errors
                BAD_ID=0
                CONNECTION=0
                IO_ERROR=0
                WRONG_LENGTH=0
                WRONG_MAP=0
                WRONG_REDUCE=0
        File Input Format Counters
                Bytes Read=16778
        File Output Format Counters
                Bytes Written=542

araucana        1
atlantica       2
australis       1
baccata 2
bignonioides    1
biloba  5
bungeana        1
cappadocicum    1
carpinifolia    4
colurna 3
coulteri        1
decurrens       1
dioicus 1
distichum       3
excelsior       1
fraxinifolia    2
giganteum       5
giraldii        1
glutinosa       1
grandiflora     1
hippocastanum   3
ilex    1
involucrata     1
japonicum       1
kaki    2
libanii 2
monspessulanum  1
nigra   3
nigra laricio   1
opalus  1
orientalis      8
papyrifera      1
petraea 2
pomifera        1
pseudoacacia    1
sempervirens    1
serrata 1
stenoptera      1
suber   1
sylvatica       8
tomentosa       2
tulipifera      2
ulmoides        1
virginiana      2
x acerifolia    11

```

### 1.8.4 Maximum height per kind of tree 

```
[ines.mankour@hadoop-edge01 ~]$ run_job tallestTree trees.csv tallest_trees; printf "\n"; result tallest_trees



[...]
21/11/17 20:20:45 INFO mapreduce.Job: The url to track the job: https://hadoop-master02.efrei.online:8090/proxy/application_1637167060931_0032/
21/11/17 20:20:45 INFO mapreduce.Job: Running job: job_1637167060931_0032
21/11/17 20:20:55 INFO mapreduce.Job: Job job_1637167060931_0032 running in uber mode : false
21/11/17 20:20:55 INFO mapreduce.Job:  map 0% reduce 0%
21/11/17 20:21:03 INFO mapreduce.Job:  map 100% reduce 0%
21/11/17 20:21:09 INFO mapreduce.Job:  map 100% reduce 100%
21/11/17 20:21:09 INFO mapreduce.Job: Job job_1637167060931_0032 completed successfully
21/11/17 20:21:09 INFO mapreduce.Job: Counters: 54
        File System Counters
                FILE: Number of bytes read=727
                FILE: Number of bytes written=528151
                FILE: Number of read operations=0
                FILE: Number of large read operations=0
                FILE: Number of write operations=0
                HDFS: Number of bytes read=16883
                HDFS: Number of bytes written=675
                HDFS: Number of read operations=8
                HDFS: Number of large read operations=0
                HDFS: Number of write operations=2
                HDFS: Number of bytes read erasure-coded=0
        Job Counters
                Launched map tasks=1
                Launched reduce tasks=1
                Data-local map tasks=1
                Total time spent by all maps in occupied slots (ms)=19440
                Total time spent by all reduces in occupied slots (ms)=9788
                Total time spent by all map tasks (ms)=6480
                Total time spent by all reduce tasks (ms)=2447
                Total vcore-milliseconds taken by all map tasks=6480
                Total vcore-milliseconds taken by all reduce tasks=2447
                Total megabyte-milliseconds taken by all map tasks=9953280
                Total megabyte-milliseconds taken by all reduce tasks=5011456
        Map-Reduce Framework
                Map input records=98
                Map output records=96
                Map output bytes=1369
                Map output materialized bytes=727
                Input split bytes=105
                Combine input records=96
                Combine output records=45
                Reduce input groups=45
                Reduce shuffle bytes=727
                Reduce input records=45
                Reduce output records=45
                Spilled Records=90
                Shuffled Maps =1
                Failed Shuffles=0
                Merged Map outputs=1
                GC time elapsed (ms)=211
                CPU time spent (ms)=2180
                Physical memory (bytes) snapshot=1450074112
                Virtual memory (bytes) snapshot=7283908608
                Total committed heap usage (bytes)=1514143744
                Peak Map Physical memory (bytes)=1160392704
                Peak Map Virtual memory (bytes)=3403067392
                Peak Reduce Physical memory (bytes)=289681408
                Peak Reduce Virtual memory (bytes)=3880841216
        Shuffle Errors
                BAD_ID=0
                CONNECTION=0
                IO_ERROR=0
                WRONG_LENGTH=0
                WRONG_MAP=0
                WRONG_REDUCE=0
        File Input Format Counters
                Bytes Read=16778
        File Output Format Counters
                Bytes Written=675

araucana        9.0
atlantica       25.0
australis       16.0
baccata 13.0
bignonioides    15.0
biloba  33.0
bungeana        10.0
cappadocicum    16.0
carpinifolia    30.0
colurna 20.0
coulteri        14.0
decurrens       20.0
dioicus 10.0
distichum       35.0
excelsior       30.0
fraxinifolia    27.0
giganteum       35.0
giraldii        35.0
glutinosa       16.0
grandiflora     12.0
hippocastanum   30.0
ilex    15.0
involucrata     12.0
japonicum       10.0
kaki    14.0
libanii 30.0
monspessulanum  12.0
nigra   30.0
nigra laricio   30.0
opalus  15.0
orientalis      34.0
papyrifera      12.0
petraea 31.0
pomifera        13.0
pseudoacacia    11.0
sempervirens    30.0
serrata 18.0
stenoptera      30.0
suber   10.0
sylvatica       30.0
tomentosa       20.0
tulipifera      35.0
ulmoides        12.0
virginiana      14.0
x acerifolia    45.0
```

### 1.8.5 Sort the trees height from smallest to largest 

```
[ines.mankour@hadoop-edge01 ~]$ run_job heightSort trees.csv height_sort; printf "\n"; result height_sort



21/11/17 20:26:37 INFO impl.TimelineReaderClientImpl: Initialized TimelineReader URI=https://hadoop-master03.efrei.online:8199/ws/v2/timeline/, clusterId=yarn-cluster
21/11/17 20:26:37 INFO client.AHSProxy: Connecting to Application History server at hadoop-master03.efrei.online/163.172.102.23:10200
21/11/17 20:26:37 INFO hdfs.DFSClient: Created token for ines.mankour: HDFS_DELEGATION_TOKEN owner=ines.mankour@EFREI.ONLINE, renewer=yarn, realUser=, issueDate=1637177197834, maxDate=1637781997834, sequenceNumber=8018, masterKeyId=93 on ha-hdfs:efrei
21/11/17 20:26:37 INFO security.TokenCache: Got dt for hdfs://efrei; Kind: HDFS_DELEGATION_TOKEN, Service: ha-hdfs:efrei, Ident: (token for ines.mankour: HDFS_DELEGATION_TOKEN owner=ines.mankour@EFREI.ONLINE, renewer=yarn, realUser=, issueDate=1637177197834, maxDate=1637781997834, sequenceNumber=8018, masterKeyId=93)
21/11/17 20:26:37 INFO client.ConfiguredRMFailoverProxyProvider: Failing over to rm2
21/11/17 20:26:38 INFO mapreduce.JobResourceUploader: Disabling Erasure Coding for path: /user/ines.mankour/.staging/job_1637167060931_0034
21/11/17 20:26:39 INFO input.FileInputFormat: Total input files to process : 1
21/11/17 20:26:39 INFO mapreduce.JobSubmitter: number of splits:1
21/11/17 20:26:39 INFO mapreduce.JobSubmitter: Submitting tokens for job: job_1637167060931_0034
21/11/17 20:26:39 INFO mapreduce.JobSubmitter: Executing with tokens: [Kind: HDFS_DELEGATION_TOKEN, Service: ha-hdfs:efrei, Ident: (token for ines.mankour: HDFS_DELEGATION_TOKEN owner=ines.mankour@EFREI.ONLINE, renewer=yarn, realUser=, issueDate=1637177197834, maxDate=1637781997834, sequenceNumber=8018, masterKeyId=93)]
21/11/17 20:26:39 INFO conf.Configuration: found resource resource-types.xml at file:/etc/hadoop/1.0.3.0-223/0/resource-types.xml
21/11/17 20:26:39 INFO impl.TimelineClientImpl: Timeline service address: hadoop-master03.efrei.online:8190
21/11/17 20:26:39 INFO impl.YarnClientImpl: Submitted application application_1637167060931_0034
21/11/17 20:26:40 INFO mapreduce.Job: The url to track the job: https://hadoop-master02.efrei.online:8090/proxy/application_1637167060931_0034/
21/11/17 20:26:40 INFO mapreduce.Job: Running job: job_1637167060931_0034
21/11/17 20:26:49 INFO mapreduce.Job: Job job_1637167060931_0034 running in uber mode : false
21/11/17 20:26:49 INFO mapreduce.Job:  map 0% reduce 0%
21/11/17 20:26:58 INFO mapreduce.Job:  map 100% reduce 0%
21/11/17 20:27:03 INFO mapreduce.Job:  map 100% reduce 100%
21/11/17 20:27:03 INFO mapreduce.Job: Job job_1637167060931_0034 completed successfully
21/11/17 20:27:03 INFO mapreduce.Job: Counters: 54
        File System Counters
                FILE: Number of bytes read=4100
                FILE: Number of bytes written=535263
                FILE: Number of read operations=0
                FILE: Number of large read operations=0
                FILE: Number of write operations=0
                HDFS: Number of bytes read=16883
                HDFS: Number of bytes written=3994
                HDFS: Number of read operations=8
                HDFS: Number of large read operations=0
                HDFS: Number of write operations=2
                HDFS: Number of bytes read erasure-coded=0
        Job Counters
                Launched map tasks=1
                Launched reduce tasks=1
                Data-local map tasks=1
                Total time spent by all maps in occupied slots (ms)=19173
                Total time spent by all reduces in occupied slots (ms)=9520
                Total time spent by all map tasks (ms)=6391
                Total time spent by all reduce tasks (ms)=2380
                Total vcore-milliseconds taken by all map tasks=6391
                Total vcore-milliseconds taken by all reduce tasks=2380
                Total megabyte-milliseconds taken by all map tasks=9816576
                Total megabyte-milliseconds taken by all reduce tasks=4874240
        Map-Reduce Framework
                Map input records=98
                Map output records=96
                Map output bytes=3902
                Map output materialized bytes=4100
                Input split bytes=105
                Combine input records=0
                Combine output records=0
                Reduce input groups=28
                Reduce shuffle bytes=4100
                Reduce input records=96
                Reduce output records=96
                Spilled Records=192
                Shuffled Maps =1
                Failed Shuffles=0
                Merged Map outputs=1
                GC time elapsed (ms)=180
                CPU time spent (ms)=2500
                Physical memory (bytes) snapshot=1456812032
                Virtual memory (bytes) snapshot=7284498432
                Total committed heap usage (bytes)=1513619456
                Peak Map Physical memory (bytes)=1161940992
                Peak Map Virtual memory (bytes)=3402866688
                Peak Reduce Physical memory (bytes)=294871040
                Peak Reduce Virtual memory (bytes)=3881631744
        Shuffle Errors
                BAD_ID=0
                CONNECTION=0
                IO_ERROR=0
                WRONG_LENGTH=0
                WRONG_MAP=0
                WRONG_REDUCE=0
        File Input Format Counters
                Bytes Read=16778
        File Output Format Counters
                Bytes Written=3994

3 - Fagus sylvatica (Fagaceae)  2.0
89 - Taxus baccata (Taxaceae)   5.0
62 - Cedrus atlantica (Pinaceae)        6.0
39 - Araucaria araucana (Araucariaceae) 9.0
44 - Styphnolobium japonicum (Fabaceae) 10.0
32 - Quercus suber (Fagaceae)   10.0
95 - Pinus bungeana (Pinaceae)  10.0
61 - Gymnocladus dioicus (Fabaceae)     10.0
63 - Fagus sylvatica (Fagaceae) 10.0
4 - Robinia pseudoacacia (Fabaceae)     11.0
93 - Diospyros virginiana (Ebenaceae)   12.0
66 - Magnolia grandiflora (Magnoliaceae)        12.0
50 - Zelkova carpinifolia (Ulmaceae)    12.0
7 - Eucommia ulmoides (Eucomiaceae)     12.0
48 - Acer monspessulanum (Sapindacaees) 12.0
58 - Diospyros kaki (Ebenaceae) 12.0
33 - Broussonetia papyrifera (Moraceae) 12.0
71 - Davidia involucrata (Cornaceae)    12.0
36 - Taxus baccata (Taxaceae)   13.0
6 - Maclura pomifera (Moraceae) 13.0
68 - Diospyros kaki (Ebenaceae) 14.0
96 - Pinus coulteri (Pinaceae)  14.0
94 - Diospyros virginiana (Ebenaceae)   14.0
91 - Acer opalus (Sapindaceae)  15.0
5 - Catalpa bignonioides (Bignoniaceae) 15.0
70 - Fagus sylvatica (Fagaceae) 15.0
2 - Ulmus carpinifolia (Ulmaceae)       15.0
98 - Quercus ilex (Fagaceae)    15.0
28 - Alnus glutinosa (Betulaceae)       16.0
78 - Acer cappadocicum (Sapindaceae)    16.0
75 - Zelkova carpinifolia (Ulmaceae)    16.0
16 - Celtis australis (Cannabaceae)     16.0
64 - Ginkgo biloba (Ginkgoaceae)        18.0
83 - Zelkova serrata (Ulmaceae) 18.0
23 - Aesculus hippocastanum (Sapindaceae)       18.0
60 - Fagus sylvatica (Fagaceae) 18.0
34 - Corylus colurna (Betulaceae)       20.0
51 - Platanus x acerifolia (Platanaceae)        20.0
43 - Tilia tomentosa (Malvaceae)        20.0
15 - Corylus colurna (Betulaceae)       20.0
11 - Calocedrus decurrens (Cupressaceae)        20.0
1 - Corylus colurna (Betulaceae)        20.0
8 - Platanus orientalis (Platanaceae)   20.0
20 - Fagus sylvatica (Fagaceae) 20.0
35 - Paulownia tomentosa (Paulowniaceae)        20.0
12 - Sequoiadendron giganteum (Taxodiaceae)     20.0
87 - Taxodium distichum (Taxodiaceae)   20.0
13 - Platanus orientalis (Platanaceae)  20.0
10 - Ginkgo biloba (Ginkgoaceae)        22.0
47 - Aesculus hippocastanum (Sapindaceae)       22.0
86 - Platanus orientalis (Platanaceae)  22.0
14 - Pterocarya fraxinifolia (Juglandaceae)     22.0
88 - Liriodendron tulipifera (Magnoliaceae)     22.0
18 - Fagus sylvatica (Fagaceae) 23.0
24 - Cedrus atlantica (Pinaceae)        25.0
31 - Ginkgo biloba (Ginkgoaceae)        25.0
92 - Platanus x acerifolia (Platanaceae)        25.0
49 - Platanus orientalis (Platanaceae)  25.0
97 - Pinus nigra (Pinaceae)     25.0
84 - Ginkgo biloba (Ginkgoaceae)        25.0
73 - Platanus orientalis (Platanaceae)  26.0
65 - Pterocarya fraxinifolia (Juglandaceae)     27.0
42 - Platanus orientalis (Platanaceae)  27.0
85 - Juglans nigra (Juglandaceae)       28.0
76 - Pinus nigra laricio (Pinaceae)     30.0
19 - Quercus petraea (Fagaceae) 30.0
72 - Sequoiadendron giganteum (Taxodiaceae)     30.0
54 - Pterocarya stenoptera (Juglandaceae)       30.0
29 - Zelkova carpinifolia (Ulmaceae)    30.0
27 - Sequoia sempervirens (Taxodiaceae) 30.0
25 - Fagus sylvatica (Fagaceae) 30.0
41 - Platanus x acerifolia (Platanaceae)        30.0
77 - Taxodium distichum (Taxodiaceae)   30.0
55 - Platanus x acerifolia (Platanaceae)        30.0
69 - Pinus nigra (Pinaceae)     30.0
38 - Fagus sylvatica (Fagaceae) 30.0
59 - Sequoiadendron giganteum (Taxodiaceae)     30.0
52 - Fraxinus excelsior (Oleaceae)      30.0
37 - Cedrus libanii (Pinaceae)  30.0
22 - Cedrus libanii (Pinaceae)  30.0
30 - Aesculus hippocastanum (Sapindaceae)       30.0
80 - Quercus petraea (Fagaceae) 31.0
9 - Platanus orientalis (Platanaceae)   31.0
82 - Platanus x acerifolia (Platanaceae)        32.0
46 - Ginkgo biloba (Ginkgoaceae)        33.0
45 - Platanus orientalis (Platanaceae)  34.0
56 - Taxodium distichum (Taxodiaceae)   35.0
81 - Liriodendron tulipifera (Magnoliaceae)     35.0
17 - Platanus x acerifolia (Platanaceae)        35.0
53 - Ailanthus giraldii (Simaroubaceae) 35.0
57 - Sequoiadendron giganteum (Taxodiaceae)     35.0
26 - Platanus x acerifolia (Platanaceae)        40.0
74 - Platanus x acerifolia (Platanaceae)        40.0
40 - Platanus x acerifolia (Platanaceae)        40.0
90 - Platanus x acerifolia (Platanaceae)        42.0
21 - Platanus x acerifolia (Platanaceae)        45.0
```

### 1.8.6 District containing the oldest tree 



```
[ines.mankour@hadoop-edge01 ~]$ run_job oldestTree trees.csv oldesttree; printf "\n"; result oldesttree

21/11/17 20:53:51 INFO impl.TimelineReaderClientImpl: Initialized TimelineReader URI=https://hadoop-master03.efrei.online:8199/ws/v2/timeline/, clusterId=yarn-cluster
21/11/17 20:53:51 INFO client.AHSProxy: Connecting to Application History server at hadoop-master03.efrei.online/163.172.102.23:10200
21/11/17 20:53:52 INFO hdfs.DFSClient: Created token for ines.mankour: HDFS_DELEGATION_TOKEN owner=ines.mankour@EFREI.ONLINE, renewer=yarn, realUser=, issueDate=1637178832090, maxDate=1637783632090, sequenceNumber=8021, masterKeyId=93 on ha-hdfs:efrei
21/11/17 20:53:52 INFO security.TokenCache: Got dt for hdfs://efrei; Kind: HDFS_DELEGATION_TOKEN, Service: ha-hdfs:efrei, Ident: (token for ines.mankour: HDFS_DELEGATION_TOKEN owner=ines.mankour@EFREI.ONLINE, renewer=yarn, realUser=, issueDate=1637178832090, maxDate=1637783632090, sequenceNumber=8021, masterKeyId=93)
21/11/17 20:53:52 INFO client.ConfiguredRMFailoverProxyProvider: Failing over to rm2
21/11/17 20:53:52 INFO mapreduce.JobResourceUploader: Disabling Erasure Coding for path: /user/ines.mankour/.staging/job_1637167060931_0037
21/11/17 20:53:53 INFO input.FileInputFormat: Total input files to process : 1
21/11/17 20:53:53 INFO mapreduce.JobSubmitter: number of splits:1
21/11/17 20:53:53 INFO mapreduce.JobSubmitter: Submitting tokens for job: job_1637167060931_0037
21/11/17 20:53:53 INFO mapreduce.JobSubmitter: Executing with tokens: [Kind: HDFS_DELEGATION_TOKEN, Service: ha-hdfs:efrei, Ident: (token for ines.mankour: HDFS_DELEGATION_TOKEN owner=ines.mankour@EFREI.ONLINE, renewer=yarn, realUser=, issueDate=1637178832090, maxDate=1637783632090, sequenceNumber=8021, masterKeyId=93)]
21/11/17 20:53:53 INFO conf.Configuration: found resource resource-types.xml at file:/etc/hadoop/1.0.3.0-223/0/resource-types.xml
21/11/17 20:53:53 INFO impl.TimelineClientImpl: Timeline service address: hadoop-master03.efrei.online:8190
21/11/17 20:53:54 INFO impl.YarnClientImpl: Submitted application application_1637167060931_0037
21/11/17 20:53:54 INFO mapreduce.Job: The url to track the job: https://hadoop-master02.efrei.online:8090/proxy/application_1637167060931_0037/
21/11/17 20:53:54 INFO mapreduce.Job: Running job: job_1637167060931_0037
21/11/17 20:54:04 INFO mapreduce.Job: Job job_1637167060931_0037 running in uber mode : false
21/11/17 20:54:04 INFO mapreduce.Job:  map 0% reduce 0%
21/11/17 20:54:12 INFO mapreduce.Job:  map 100% reduce 0%
21/11/17 20:54:17 INFO mapreduce.Job:  map 100% reduce 100%
21/11/17 20:54:17 INFO mapreduce.Job: Job job_1637167060931_0037 completed successfully
21/11/17 20:54:17 INFO mapreduce.Job: Counters: 54
        File System Counters
                FILE: Number of bytes read=1315
                FILE: Number of bytes written=529739
                FILE: Number of read operations=0
                FILE: Number of large read operations=0
                FILE: Number of write operations=0
                HDFS: Number of bytes read=16883
                HDFS: Number of bytes written=7
                HDFS: Number of read operations=8
                HDFS: Number of large read operations=0
                HDFS: Number of write operations=2
                HDFS: Number of bytes read erasure-coded=0
        Job Counters
                Launched map tasks=1
                Launched reduce tasks=1
                Data-local map tasks=1
                Total time spent by all maps in occupied slots (ms)=18147
                Total time spent by all reduces in occupied slots (ms)=10056
                Total time spent by all map tasks (ms)=6049
                Total time spent by all reduce tasks (ms)=2514
                Total vcore-milliseconds taken by all map tasks=6049
                Total vcore-milliseconds taken by all reduce tasks=2514
                Total megabyte-milliseconds taken by all map tasks=9291264
                Total megabyte-milliseconds taken by all reduce tasks=5148672
        Map-Reduce Framework
                Map input records=98
                Map output records=77
                Map output bytes=1155
                Map output materialized bytes=1315
                Input split bytes=105
                Combine input records=0
                Combine output records=0
                Reduce input groups=1
                Reduce shuffle bytes=1315
                Reduce input records=77
                Reduce output records=1
                Spilled Records=154
                Shuffled Maps =1
                Failed Shuffles=0
                Merged Map outputs=1
                GC time elapsed (ms)=216
                CPU time spent (ms)=2390
                Physical memory (bytes) snapshot=1453477888
                Virtual memory (bytes) snapshot=7282536448
                Total committed heap usage (bytes)=1509425152
                Peak Map Physical memory (bytes)=1159708672
                Peak Map Virtual memory (bytes)=3403489280
                Peak Reduce Physical memory (bytes)=293769216
                Peak Reduce Virtual memory (bytes)=3879047168
        Shuffle Errors
                BAD_ID=0
                CONNECTION=0
                IO_ERROR=0
                WRONG_LENGTH=0
                WRONG_MAP=0
                WRONG_REDUCE=0
        File Input Format Counters
                Bytes Read=16778
        File Output Format Counters
                Bytes Written=7

1601    5
```
We can see here that the oldest tree is in 5th district and is born in 1601