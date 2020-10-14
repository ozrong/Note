# pd.DataFrame()保存

DataFrame是Python中Pandas库中的一种数据结构，它类似excel，是一种二维表,DataFrame的单元格可以存放数值、字符串等，这和excel表很像，同时DataFrame可以设置列名columns与行名index。

## DataFrame的构建方式

### 数据为字典结构

#### 字典结构1

```python
 """
{列名1:[行数据列表1]
 ，列名2:[行数据列表2]
 ，列名3:[行数据列表3]}
 
这种结构中将自动生成列名
"""
#eg:
df1= pd.DataFrame({'source':   ['andriod','windows','iphone','linux','360浏览器']
                  ,'count':[45,12,80,45,24]})
df1.to_csv("e.csv",encoding="utf_8_sig")
```

数据保存的形式（exel打开）：

![image-20200826101203840](C:\Users\hp\AppData\Roaming\Typora\typora-user-images\image-20200826101203840.png)

记事本打开：

![image-20200826101302153](C:\Users\hp\AppData\Roaming\Typora\typora-user-images\image-20200826101302153.png)

==**注意：**==数据里面有中文的话就要设置编码，`encoding="utf_8_sig"`(没有写错就是“utf_8_sig”)，使用''utf-8''的csv文件在使用记事本打开的时候不会乱码，但是在用exel打开的时候就会乱码

#### 字典结构2

```python
"""
  {索引1:值1
 ，索引2:值2
 ，索引3:值3}

这种结构在生成DataFrame时需要指定列名，并注意传入的时dict.items()。
即字典键值对构成的元组列表
"""
#eg
df2= pd.DataFrame({'andriod':23
                       ,'windows':56
                       ,'iphone':45
                       ,'linux':12
                       ,'360浏览器':34
                      }.items(),columns=['source','count'])
df2.to_csv("f.csv",encoding="utf_8_sig")


#补充说明
"""
dict.items()会让字典变为字典键值对构成的元组列表，即：
[('andriod', 23), ('windows', 56), ('iphone', 45), ('linux', 12), ('360浏览器', 34)]
其中('andriod', 23)这种就是元组
取值:  value1,value2 = ('andriod', 23)

"""
```

数据形式：

![image-20200826102206032](C:\Users\hp\AppData\Roaming\Typora\typora-user-images\image-20200826102206032.png)

### 数据为二维数组

```python
#通过二维数组来创建(data：二维数组)，索引与列名可通过列表参数的形式传入，但需要保证长度匹配
a =[[1, 2, 3],[2, 3, 4],[3, 4, 5]] #假设这就是数组
df3 = pd.DataFrame(a,index= list('123'),columns=list('abc'))
df3.to_csv("f.csv",encoding="utf_8_sig")
```

==索引与列名可通过列表参数的形式传入，但需要保证索引和列名的长度要和数据一致==

数据形式：

![image-20200826102936231](C:\Users\hp\AppData\Roaming\Typora\typora-user-images\image-20200826102936231.png)

```python
a =[[1, 2, 3],[2, 3, 4],[3, 4, 5]]
df3 = pd.DataFrame(a,index=None,columns=["value1","value2","value3"]) #可以是这种列表来指定列名，index=None表示使用默认的索引 
                                                                      # (0,1，.......)
df3.to_csv("k.csv",index=False,encoding="utf_8_sig")   #index=False就是不保存所以到csv文件中
```

数据形式：

![image-20200826103333077](C:\Users\hp\AppData\Roaming\Typora\typora-user-images\image-20200826103333077.png)

# pd.read_csv()读取

```python
def read_csv(
    filepath_or_buffer: FilePathOrBuffer,
    sep=",",
    delimiter=None,
    # Column and Index Locations and Names
    header="infer",
    names=None,
    index_col=None,
    usecols=None,
    squeeze=False,
    prefix=None,
    mangle_dupe_cols=True,
    # General Parsing Configuration
    dtype=None,
    engine=None,
    converters=None,
    true_values=None,
    false_values=None,
    skipinitialspace=False,
    skiprows=None,
    skipfooter=0,
    nrows=None,
    # NA and Missing Data Handling
    na_values=None,
    keep_default_na=True,
    na_filter=True,
    verbose=False,
    skip_blank_lines=True,
    # Datetime Handling
    parse_dates=False,
    infer_datetime_format=False,
    keep_date_col=False,
    date_parser=None,
    dayfirst=False,
    cache_dates=True,
    # Iteration
    iterator=False,
    chunksize=None,
    # Quoting, Compression, and File Format
    compression="infer",
    thousands=None,
    decimal: str = ".",
    lineterminator=None,
    quotechar='"',
    quoting=csv.QUOTE_MINIMAL,
    doublequote=True,
    escapechar=None,
    comment=None,
    encoding=None,
    dialect=None,
    # Error Handling
    error_bad_lines=True,
    warn_bad_lines=True,
    # Internal
    delim_whitespace=False,
    low_memory=_c_parser_defaults["low_memory"],
    memory_map=False,
    float_precision=None,
)
下面来看常用参数：
1.filepath_or_buffer:（这是唯一一个必须有的参数，其它都是按需求选用的）文件所在处的路径,路径 URL 可以是http, ftp, s3, 和 file(.txt .dat)，都行

2.sep：
指定分隔符，默认为逗号','

3.delimiter : str, default None
定界符，备选分隔符（如果指定该参数，则sep参数失效）

4.header：int or list of ints, default ‘infer’
指定哪一行作为表头。默认设置为0（即第一行作为表头），如果没有表头的话，要修改参数，设置header=None

5.names：
指定列的名称，用列表表示。一般我们没有表头，即header=None时，这个用来添加列名就很有用啦！

6.index_col:
指定哪一列数据作为行索引，可以是一列，也可以多列。多列的话，会看到一个分层索引

7.prefix:
给列名添加前缀。如prefix="x",会出来"x1"、"x2"、"x3"酱纸

8.nrows : int, default None
需要读取的行数（从文件头开始算起）

9.encoding:
乱码的时候用这个就是了

10.skiprows : list-like or integer, default None
需要忽略的行数（从文件开始处算起），或需要跳过的行号列表（从0开始）
#eg:
full_data = pd.read_csv(DATA_DIR,sep='::',header=None,names=['user','item'],
                        usecols=[0,1],dtype={0:np.int32,1:np.int32},engine='python') 
只取了0,1两列，并取名为['user','item'],数据类型定义为{0:np.int32,1:np.int32}
"""
后面就可以使用full_data.user(或者full_data['user'])把数据当做列表进行操作了 
"""
```

