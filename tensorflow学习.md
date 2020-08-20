# 1.加载数据集

**tf.data.Dataset.from_tensor_slices五步加载数据集:**

```python
#=====准备数据集（原始）=========
data = np.load('data/test_data.npy').item()
"""假如数据是这样的：
data字典 {“user”:[user_Id,user_Id...............],"item":[item_id,item_id,item_id................]。"label":[1,1,1,1,1,0,0,0.......]}
"""

#=====数据集批量化 =======
dataset = tf.data.Dataset.from_tensor_slices(data)
"""
之后数据会变成：
    {'user': 0, 'item': 1154, 'label': 1}
    {'user': 0, 'item': 3005, 'label': 1}
    {'user': 0, 'item': 2119, 'label': 1}
    {'user': 0, 'item': 1760, 'label': 1}
    {'user': 0, 'item': 1631, 'label': 1}
    {'user': 0, 'item': 260,  'label': 0}
"""

dataset = dataset.shuffle(10000).batch(256)
"""	
打乱，批量化

data可以是nmupy的字典
  将我们的Dataset变成一个BatchDataset，这样的话，在迭代数据的时候，就可以一次返回  一个batch大小的数据

"""
#====构造迭代器=====
"""
此时dataset有两个属性，分别是output_shapes和output_types。我们将根据这两个属性来构造迭代器，用于迭代数据
"""
iterator = tf.data.Iterator.from_structure(dataset.output_types,
                                           dataset.output_shapes)
#返回的迭代器没有绑定到特定的数据集
#迭代器需要初始化
 sess.run(iterator.make_initializer(dataset))
 
#===== 获取batch大小的数据
def getBatch():
    sample = iterator.get_next()
    print(sample)
    user = sample['user']
    item = sample['item']
    return user,item

#====使用迭代器=====
#eg：我们这里来计算返回的每个batch中，user和item的平均值：
users,items = getBatch()
usersum = tf.reduce_mean(users,axis=-1)
itemsum = tf.reduce_mean(items,axis=-1)

#====注：迭代器iterator只能往前遍历，如果遍历完之后还调用get_next()的话，会报tf.errors.OutOfRangeError错误，因此需要使用try-catch========
try:
    while True:
        print(sess.run([usersum,itemsum]))
except tf.errors.OutOfRangeError:
    print("outOfRange")  
```



+ Step0: 准备要加载的numpy数据
+ Step1: 使用 tf.data.Dataset.from_tensor_slices() 函数进行加载
+ Step2: 使用 shuffle() 打乱数据
+ Step3: 使用 map() 函数进行预处理
+ Step4: 使用 batch() 函数设置 batch size 值
+ Step5: 根据需要 使用 repeat() 设置是否循环迭代数据集

shuffle(10000):每取10000个数据打乱一次

map():预处理，可以传入预处理的方法

batch():数据批量大小 eg:128

# tf.initialize_all_variable()

如果定义了变量就一定要使用这个语句初始化这个变量

```python
eg:
a=tf.Variable(tf.ones([3,3]))
#在这一步的时候只是定义了这个一个变量，其实里面是没有值的
```



Session

这是tensorflow的会话控制，

初始化有两种方式：

+ `sess=tf.Session`，Session是一个类，

+ ```python
  with tf.Session as sess:
      sess.run(...)
  ```

  run()就是想执行的语句

  ```python
  a=tf.constant(1)
  va = tf.Variable(2)
  new = tf.add(a,va)
  init = tf.initialize_all_variables()
  with tf.Session() as sess:
      sess.run(init)
      sess.run(new)
      print(sess.run(new)) #注意直接输出new是没有用的
      """output=3"""
      print(new)
      """output
      Tensor("Add:0", shape=(), dtype=int32)
      """
      """
      这样是可以的
      b=sess.run(new)
      print(b)
      """
  
  ```

  

# pleaceholder

```
input1 = tf.placeholder(tf.float32)
input2 = tf.placeholder(tf.float32)
output = tf.multiply(input1,input2)
with tf.Session() as sess:
    print(sess.run(output,feed_dict={input1:[7.],input2:[2.]}))
    
"""
input1,input2一开始是没有值的要在run的时候喂进来值
"""
```

# from_structure()

**tensorflow tf.data.Iterator.from_structure()**

~~~python
@staticmethod
  def from_structure(output_types,
                     output_shapes=None,
                     shared_name=None,
                     output_classes=None):
"""Creates a new, uninitialized `Iterator` with the given structure.
This iterator-constructing method can be used to create an iterator that
is reusable with many different datasets.

The returned iterator is not bound to a particular dataset, and it has
no `initializer`. To initialize the iterator, run the operation returned by
`Iterator.make_initializer(dataset)`.

The following is an example

用给定的结构创建一个新的未初始化的Iterator。

 此迭代器构造方法可用于创建可与许多不同数据集重复使用的迭代器。

 返回的迭代器未绑定到特定的数据集，并且没有“ initializer”。 要初始化迭代器，请运行Iterator.make_initializer（dataset）返回的操作。

 以下是一个例子

```python
iterator = Iterator.from_structure(tf.int64, tf.TensorShape([]))

dataset_range = Dataset.range(10)
range_initializer = iterator.make_initializer(dataset_range)

dataset_evens = dataset_range.filter(lambda x: x % 2 == 0)
evens_initializer = iterator.make_initializer(dataset_evens)

# Define a model based on the iterator; in this example, the model_fn
# is expected to take scalar tf.int64 Tensors as input (see
# the definition of 'iterator' above).
prediction, loss = model_fn(iterator.get_next())

# Train for `num_epochs`, where for each epoch, we first iterate over
# dataset_range, and then iterate over dataset_evens.
for _ in range(num_epochs):
  # Initialize the iterator to `dataset_range`
  sess.run(range_initializer)
  while True:
    try:
      pred, loss_val = sess.run([prediction, loss])
    except tf.errors.OutOfRangeError:
      break

  # Initialize the iterator to `dataset_evens`
  sess.run(evens_initializer)
  while True:
    try:
      pred, loss_val = sess.run([prediction, loss])
    except tf.errors.OutOfRangeError:
      break
```

Args:
  output_types: A nested structure of `tf.DType` objects corresponding to
    each component of an element of this dataset.
    			tf.DType对象的嵌套结构与该数据集元素的每个组成部分相对应。
    			
  output_shapes: (Optional.) A nested structure of `tf.TensorShape` objects
    corresponding to each component of an element of this dataset. If
    omitted, each component will have an unconstrainted shape.
    			（可选。）“ tf.TensorShape”对象的嵌套结构，与该数据集的元素的每个组成部分相对应。 
    			如果省略，则每个组件将具有不受约束的形状
    			。
  shared_name: (Optional.) If non-empty, this iterator will be shared under
    the given name across multiple sessions that share the same devices
    (e.g. when using a remote server).
    		  （可选。）如果为非空，则该迭代器将在共享相同设备的多个会话之间以给定名称共享（例如，在使用远程服务器时）。
  output_classes: (Optional.) A nested structure of Python `type` objects
    corresponding to each component of an element of this iterator. If
    omitted, each component is assumed to be of type `tf.Tensor`.
    			 （可选。）Python“类型”对象的嵌套结构，与该迭代器的元素的每个组件相对应。 如果省略，则假定每个组件的类型均为“ tf.Tensor”。

Returns:
  An `Iterator`.
  一个`迭代器`。

Raises:
  TypeError: If the structures of `output_shapes` and `output_types` are
    not the same.
  TypeError：如果output_shapes和output_types的结构不同。
"""
output_types = nest.map_structure(dtypes.as_dtype, output_types)
if output_shapes is None:
  output_shapes = nest.map_structure(
      lambda _: tensor_shape.TensorShape(None), output_types)
else:
  output_shapes = nest.map_structure_up_to(
      output_types, tensor_shape.as_shape, output_shapes)
if output_classes is None:
  output_classes = nest.map_structure(lambda _: ops.Tensor, output_types)
nest.assert_same_structure(output_types, output_shapes)
if shared_name is None:
  shared_name = ""
if compat.forward_compatible(2018, 8, 3):
  if _device_stack_is_empty():
    with ops.device("/cpu:0"):
      iterator_resource = gen_dataset_ops.iterator_v2(
          container="",
          shared_name=shared_name,
          output_types=nest.flatten(
              sparse.as_dense_types(output_types, output_classes)),
          output_shapes=nest.flatten(
              sparse.as_dense_shapes(output_shapes, output_classes)))
  else:
    iterator_resource = gen_dataset_ops.iterator_v2(
        container="",
        shared_name=shared_name,
        output_types=nest.flatten(
            sparse.as_dense_types(output_types, output_classes)),
        output_shapes=nest.flatten(
            sparse.as_dense_shapes(output_shapes, output_classes)))
else:
  iterator_resource = gen_dataset_ops.iterator(
      container="",
      shared_name=shared_name,
      output_types=nest.flatten(
          sparse.as_dense_types(output_types, output_classes)),
      output_shapes=nest.flatten(
          sparse.as_dense_shapes(output_shapes, output_classes)))
return Iterator(iterator_resource, None, output_types, output_shapes,
                output_classes)
~~~
# get_checkpoint_state()

```python
def get_checkpoint_state(checkpoint_dir, latest_filename=None):
"""
  Returns:
    A CheckpointState if the state was available, None
    otherwise.(如果检查点存在则返会检测点状态，否则返回None)
""" 
    
```

# saver = tf.train.Saver()

**saver = tf.train.Saver()**

`tf.train.Saver()`是一个==类==，提供了变量、模型(也称图Graph)的保存和恢复模型方法

TensorFlow是通过构造Graph的方式进行深度学习，任何操作(如卷积、池化等)都需要operator，保存和恢复操作也不例外。在`tf.train.Saver()`==类==初始化时，用于保存和恢复的`save`和`restore`, operator会被加入Graph。所以，下列类初始化操作应在搭建Graph时完成

```python
 class Saver(object):#这个是类
    def __init__(self,
               var_list=None,
               reshape=False,
               sharded=False,
               max_to_keep=5,
               keep_checkpoint_every_n_hours=10000.0,
               name=None,
               restore_sequentially=False,
               saver_def=None,
               builder=None,
               defer_build=False,
               allow_empty=False,
               write_version=saver_pb2.SaverDef.V2,
               pad_step_number=False,
               save_relative_paths=False,
               filename=None):
        """
        var_list:specifies the variables that will be saved and restored. It can be passed as a `dict` or a list
        
    eg:
    v1 = tf.Variable(..., name='v1')
    v2 = tf.Variable(..., name='v2')

    # Pass the variables as a dict:
    saver = tf.compat.v1.train.Saver({'v1': v1, 'v2': v2})

    # Or pass them as a list.
    saver = tf.compat.v1.train.Saver([v1, v2])
    # Passing a list is equivalent to passing a dict with the variable op names
    # as keys:
    saver = tf.compat.v1.train.Saver({v.op.name: v for v in [v1, v2]})
        
        """
```

实例化了`saver=tf.train.Saver()`使用`saver.save()`这个方法关于save():

```python
save(
	sess,  # 必需参数，Session对象
	save_path,  # 必需参数，存储路径
	global_step=None,  # 可以是Tensor, Tensor name, 整型数
	latest_filename=None,  # 协议缓冲文件名，默认为'checkpoint'，不用管
	meta_graph_suffix='meta',  # 图文件的后缀，默认为'.meta'，不用管
	write_meta_graph=True,  # 是否保存Graph
	write_state=True,  # 建议选择默认值True
	strip_default_attrs=False  # 是否跳过具有默认值的节点
```

**tensorflow的保存和恢复分为两种：保存和恢复变量，保存和恢复模型**

## 保存和恢复变量

TensorFlow会讲变量保存在二进制checkpoint文件中，这类文件会将变量名称映射到张量值

### 保存变量

1. 创建变量
2. 初始化变量
3. 实例化`tf.train.Saver()`返回的是一个类可以理解为对象
4. 创建Session并保存

```python
import tensorflow as tf
#===定义变量 =====
W = tf.Variable([[1,2,3],[3,4,5,]],dtype=tf.float32)
b = tf.Variable([[1,2,3]],dtype=tf.float32)
##====初始化变量=====
init = tf.initialize_all_variables()
## =====实例化tf.train.Saver()==============
saver = tf.train.Saver()
## =======创建session，保存变量===========
with tf.Session() as sess:
    sess.run(init)
    save_path=saver.save(sess,"data/sava_net.ckpt")
    """data/sava_net.ckpt 保存的文件路径及名字（保存过后会有4个文件在data中）如下面的图"""
    print(save_path)
```

![image-20200819210135633](C:\Users\hp\AppData\Roaming\Typora\typora-user-images\image-20200819210135633.png)

==注意到了没：==并不生成一个save_net.ckpt这样的一个文件，checkpoint保存的是当前网络状态，.meta文件保存的是Graph结构的文件

### **恢复变量**

从checkpoint文件中提取变量值赋给新定义的变量

**注意：**在使用restore恢复变量的时候，不要忘记要定义与之前保存的变量一样的shape和dtype,以及和之前保存是==一样的变量名字==

1. 定义相同名字的变量以及变量的shape,dtype
2. 实例化`saver=tf.train.Saver()`
3. 使用`saver.restore()`恢复变量

```python
import tensorflow as tf
import numpy as np
W = tf.Variable(np.arange(6).reshape((2,3)),dtype = tf.float32)
b = tf.Variable(np.arange(3).reshape((1,3)),dtype = tf.float32)
#使用restore恢复变量的时候这个变量是不用初始话变量的
saver = tf.train.Saver()
with tf.Session() as sess:
    saver.restore(sess,"data/sava_net.ckpt")
    print("w:",sess.run(W))
    print("b:",sess.run(b))
"""
output:
w: [[1. 2. 3.]
 [3. 4. 5.]]
b: [[1. 2. 3.]]

"""
```

### 有选择的保存和恢复

向`tf.train.Saver()`的构造函数传递以下任意内容来轻松指定要保存或加载的名称和变量：

+ 1.变量列表(要求变量与变量名之间的一一对应)
+ 2.Python字典，其中，key是要使用的名称，value是要管理的变量(通过键值映射自定义变量与变量名之间的对应关系)

```python
#假如只保存上述例子中的变量
W = tf.Variable([[1,2,3],[3,4,5,]],dtype=tf.float32)
b = tf.Variable([[1,2,3]],dtype=tf.float32)
init = tf.initialize_all_variables()
saver = tf.train.Saver([W])
"""
或者saver = tf.train.Saver({"W_name":W})
不过在恢复的使用要使用一样的方式得到saver
"""
with tf.Session() as sess:
    sess.run(init)
    save_path=saver.save(sess,"data/sava_net.ckpt")
    print(save_path)
    
#=========恢复===========
W = tf.Variable(np.arange(6).reshape((2,3)),dtype = tf.float32)
saver = tf.train.Saver()
with tf.Session() as sess:
    saver.restore(sess,"data/sava_net.ckpt")
    print("w:",sess.run(W))
```

### 查看ckpt二进制文件中的变量

我们可以使用 [inspect_checkpoint](https://www.github.com/tensorflow/tensorflow/blob/r1.6/tensorflow/python/tools/inspect_checkpoint.py) 库的`print_tensors_in_checkpoint_file()`快速检查某个检查点的变量。

```python
def print_tensors_in_checkpoint_file(file_name, tensor_name, all_tensors,
                                     all_tensor_names=False,
                                     count_exclude_pattern="")
"""
如果没有提供' tensor_name '，则在检查点文件中打印张量名称和形状。如果提供了' tensor_name '，则打印张量的内容。
file_name:检查点文件的名称。
tensor_name:要打印的检查点文件中张量的名称。
all_tensors:指示是否打印所有张量的布尔值。如果是False就打印指定的变量
all_tensor_names:布尔值，指示是否打印所有张量名称。如果是Ture就打打印所有的变量名
count_exclude_pattern:正则表达式字符串，计数时排除张量的模式。
"""    
```

例子：

```python
# 保存多一点的变量
W = tf.Variable([[1,2,3],[3,4,5,]],dtype=tf.float32)
b = tf.Variable([[1,2,3]],dtype=tf.float32)
c = tf.Variable([[7,8,9]],dtype=tf.float32)
init = tf.initialize_all_variables()
#saver = tf.train.Saver([W])
saver = tf.train.Saver({"W_name":W,"b_name":b,"c_name":c})
with tf.Session() as sess:
    sess.run(init)
    save_path=saver.save(sess,"data/sava_net.ckpt")
    print(save_path)
#=========读取检查点的变量=======================
from tensorflow.python.tools import inspect_checkpoint as ickpt
ickpt.print_tensors_in_checkpoint_file("data/sava_net.ckpt", tensor_name="W_name", all_tensors=False)
#注意如果之前是用字典保存的变量，那么tensor_name="W_name"是字典的Key

"""
tensor_name:  W_name
[[1. 2. 3.]
 [3. 4. 5.]]
# Total number of params: 12
"""
ickpt.print_tensors_in_checkpoint_file("data/sava_net.ckpt", tensor_name="", all_tensors=True)
#all_tensors=True的话，tensor_name=""
"""
tensor_name:  W_name
[[1. 2. 3.]
 [3. 4. 5.]]
tensor_name:  b_name
[[1. 2. 3.]]
tensor_name:  c_name
[[7. 8. 9.]]
# Total number of params: 12
"""
```

**注意：**`W = tf.Variable([[1,2,3],[3,4,5,]],dtype=tf.float32,name="W")`如果在定义变量的时候没有定义name的话，这个变量在ckpt二进制文件中，这个变量名是不会叫W的而是叫Variable（定义的第一个叫Variable，第二个叫Variable_1）

`saver = tf.train.Saver({"W_name":W,"b_name":b,"c_name":c})`使用这个方式就相当于使用name这个参数

## 保存和恢复模型

# tf.name_scope

+ 在某个tf.name_scope()指定的区域中定义的所有对象及各种操作，他们的“name”属性上会增加该命名区的区域名，用以区别对象属于哪个区域；
+ 将不同的对象及操作放在由tf.name_scope()指定的区域中，便于在tensorboard中展示清晰的逻辑关系图，这点在复杂关系图中特别重要。

**注意：**tf.name_scope()只是规定了对象和操作属于哪个区域，但这并不意味着他们的作用域也只限于该区域（with的这种写法很容易让人产生这种误会），不要将其和“全局变量、局部变量”的概念搞混淆，两者完全不是一回事。在name_scope中定义的对象，从被定义的位置开始，直到后来某个地方对该对象重新定义，中间任何地方都可以使用该对象。本质上name_scope只对对象的name属性进行圈定，并不会对其作用域产生任何影响。这就好比甲、乙、丙、丁属于陈家，这里“陈家”就是一个name_scope划定的区域，虽然他们只属于陈家，但他们依然可以去全世界的任何地方，并不会只将他们限制在陈家范围

```python
# 无tf.name_scope()
a = tf.constant(1,name='my_a') #定义常量
b = tf.Variable(2,name='my_b') #定义变量
c = tf.add(a,b,name='my_add') #二者相加（操作）
print("a.name = "+a.name)
print("b.name = "+b.name)
print("c.name = "+c.name)
"""
a.name = my_a:0
b.name = my_b:0
c.name = my_add:0
"""

# 有tf.name_scope()
with tf.name_scope('cgx_name_scope'): #定义一块名为cgx_name_scope的区域，并在其中工作
    a1 = tf.constant(1,name='my_a')
    b1 = tf.Variable(2,name='my_b')
    c1 = tf.add(a,b,name='my_add')
print("a1.name = "+a1.name)
print("b1.name = "+b1.name)
print("c1.name = "+c1.name)
"""
a1.name = cgx_name_scope/my_a:0
b1.name = cgx_name_scope/my_b:0
c1.name = cgx_name_scope/my_add:0
"""
```

# tf.one_hot()

```python
tf.one_hot(
    indices,
    depth,
    on_value=None,
    off_value=None,
    axis=None,
    dtype=None,
    name=None
)
"""
由indices指定的位置将被on_value填充, 其他位置被off_value填充
如果indices是一个标量, 函数输出将是一个长度为depth的向量
如果indices是一个长度为features的向量,则默认输出一个features*depth形状的张量
"""
eg:
    indices = [0, 1, 2]  #输入数据(是个向量)需要编码的索引是[0,1,2]
depth = 3
tf.one_hot(indices, depth)  # output: [3 x 3]
# [[1., 0., 0.],
#  [0., 1., 0.],
#  [0., 0., 1.]]
 
indices = [0, 2, -1, 1]  #输入数据(是个向量)的需要编码的索引是[0,2,-1,1]
depth = 3
tf.one_hot(indices, depth,
           on_value=5.0, off_value=0.0,
           axis=-1)  # output: [4 x 3]
# [[5.0, 0.0, 0.0],  # one_hot(0)  对位置0处的数据进行one_hot编码
#  [0.0, 0.0, 5.0],  # one_hot(2)  对位置2处的数据进行one_hot编码
#  [0.0, 0.0, 0.0],  # one_hot(-1) 对位置-1处的数据进行one_hot编码
#  [0.0, 5.0, 0.0]]  # one_hot(1)  对位置1处的数据进行one_hot编码
 
indices = [[0, 2], [1, -1]]   #输入数据是个矩阵
depth = 3
tf.one_hot(indices, depth,
           on_value=1.0, off_value=0.0,
           axis=-1)  # output: [2 x 2 x 3]
# [[[1.0, 0.0, 0.0],   # one_hot(0)  对位置(0,0)处的数据进行one_hot编码
#   [0.0, 0.0, 1.0]],  # one_hot(2)  对位置(0,2)处的数据进行one_hot编码
#  [[0.0, 1.0, 0.0],   # one_hot(1)  对位置(1,1)处的数据进行one_hot编码
#   [0.0, 0.0, 0.0]]]  # one_hot(-1) 对位置(1,-1)处的数据进行one_hot编码
 
```

#  tf.layers.dropout()

就是你在训练的时候想拿掉多少神经元，按比例计算。0就是没有dropout，1就是整个层都没了

```python
tf.layers.dropout(self.layer2_MLP, rate=self.dropout)
"""
layer2_MLP：那一层
rate,按多少比例丢
"""
dropout( inputs,  
            rate=0.5,  
            noise_shape=None,  
            seed=None,  
            training=False,  
            name=None)
"""
inputs：必须，即输入数据。
rate：可选，默认为 0.5，即 dropout rate，如设置为 0.1，则意味着会丢弃 10% 的神经元。
noise_shape：可选，默认为 None，int32 类型的一维 Tensor，它代表了 dropout mask 的 shape，dropout mask 会与 inputs 相乘对 inputs 做转换，例如 inputs 的 shape 为 (batch_size, timesteps, features)，但我们想要 droput mask 在所有 timesteps 都是相同的，我们可以设置 noise_shape=[batch_size, 1, features]。
seed：可选，默认为 None，即产生随机熟的种子值。
training：可选，默认为 False，布尔类型，即代表了是否标志位 training 模式。
name：可选，默认为 None，dropout 层的名称。
返回： 经过 dropout 层之后的 Tensor
"""
```

# tf.layers.dense（）

```python
def dense(
    inputs, 
    units,
    activation=None,
    use_bias=True,
    kernel_initializer=None,
    bias_initializer=init_ops.zeros_initializer(),
    kernel_regularizer=None,
    bias_regularizer=None,
    activity_regularizer=None,
    kernel_constraint=None,
    bias_constraint=None,
    trainable=True,
    name=None,
    reuse=None):
    """
    全连接层
    该层实现操作:  outputs = activation(inputs * kernel + bias)
    其中‘激活’是作为‘activation’参数传递的激活函数(如果不是‘None’)，‘kernel’是     一个由层创建的权重矩阵，而‘bias’是一个由层创建的偏差向量
    inputs: Tensor input
    units：整数或长，维数的输出空间。
    use_bias: Boolean, whether the layer uses a bias
     kernel_initializer:权值矩阵的初始化函数。如果“None”(默认)，则使用默认值初始化权重by `tf.compat.v1.get_variable`.
    bias_initializer: Initializer function for the bias.
    kernel_regularizer: 权值矩阵的正则化函数。
    bias_regularizer:偏差的初始化函数
    activity_regularizer: Regularizer function for the output.
    """
```



# shape注意事项

[1,2] ----> shape=(2,) 表示一维数组，里面有2个元素

[[1],[2]]----> shape=(2,1)表示一个二维数组，2行1列

==[[1,2]]----->shape=(1,2) 表示二维数组？？？？？，==

```
tf.reshape(input,[-1])
"""
input是输入的数据，shape=[-1]就是不管input里面是什么把它变为1维的（也就是平铺）
"""
```

# tensorflow的数学运算

## reduce_mean()

```python
reduce_mean(input_tensor,
                axis=None,
                keep_dims=False,
                name=None,
                reduction_indices=None)
"""
第一个参数input_tensor： 输入的待降维的tensor;
第二个参数axis： 指定的轴，如果不指定，则计算所有元素的均值;
第三个参数keep_dims：是否降维度，设置为True，输出的结果保持输入tensor的形状，设置为False，输出结果会降低维度;
第四个参数name： 操作的名称;
第五个参数 reduction_indices：在以前版本中用来指定轴，已弃用;

"""
```

## tf.multiply（）

两个矩阵中对应元素各自相乘。

```python
import tensorflow as tf  
 
#两个矩阵相乘
x=tf.constant([[1.0,2.0,3.0],[1.0,2.0,3.0],[1.0,2.0,3.0]])  
y=tf.constant([[0,0,1.0],[0,0,1.0],[0,0,1.0]])
#注意这里这里x,y要有相同的数据类型，不然就会因为数据类型不匹配而出错
z=tf.multiply(x,y)
 
#两个数相乘
x1=tf.constant(1)
y1=tf.constant(2)
#注意这里这里x1,y1要有相同的数据类型，不然就会因为数据类型不匹配而出错
z1=tf.multiply(x1,y1)
 
#数和矩阵相乘
x2=tf.constant([[1.0,2.0,3.0],[1.0,2.0,3.0],[1.0,2.0,3.0]])
y2=tf.constant(2.0)
#注意这里这里x1,y1要有相同的数据类型，不然就会因为数据类型不匹配而出错
z2=tf.multiply(x2,y2)
 
with tf.Session() as sess:  
    print(sess.run(z))
    print(sess.run(z1))
    print(sess.run(z2))#这里使用了广播机制
"""
output:
z [[0. 0. 3.]
 [0. 0. 3.]
 [0. 0. 3.]]
 
z1 2

z2 [[2. 4. 6.]
 [2. 4. 6.]
 [2. 4. 6.]]
 
   
"""
```



# 张量的创建

## tf.concat（）

tensorflow中用来拼接张量的函数tf.concat()，用法:

`tf.concat([tensor1, tensor2, tensor3,...], axis)`

```python
  t1 = [[1, 2, 3], [4, 5, 6]]
  t2 = [[7, 8, 9], [10, 11, 12]]
  tf.concat([t1, t2], 0)  # [[1, 2, 3], [4, 5, 6], [7, 8, 9], [10, 11, 12]]
  tf.concat([t1, t2], 1)  # [[1, 2, 3, 7, 8, 9], [4, 5, 6, 10, 11, 12]]
 
  # tensor t3 with shape [2, 3]
  # tensor t4 with shape [2, 3]
  tf.shape(tf.concat([t3, t4], 0))  # [4, 3]
  tf.shape(tf.concat([t3, t4], 1))  # [2, 6]
```



# tensorflow网络中变量的创建

通过tf.get_variables()和tf.Variable()来创建变量是等价的。

```python
#等价的两种形式
v = tf.get_variable('v',shape=[1],initializer=tf.constant_initializer(1.0))
v1 = tf.Variable(tf.constant(1.0,shape=[1],name='v')
"""
tf.constant_initializer，将变量转化为指定常量
tf.random_normalni_initializer，将变量初始化为指定的正态分布随机值
tf.truncated_normal_initializer，截断正态分布，如果随机值偏离平均值超过2个标准差
那么那个数将重新随机
tf.random_unitform_initializer，指定的均匀分布随机值
tf.unitform_unit_scaling_initializer，满足均匀分布但不影响输出数量级的随机值
tf.zeros_initializer，零矩阵
tf.ones_initializer，全为1的矩阵
"""
                
```



# tf.nn.top_k()

```python
tf.nn.top_k(input, k, name=None)
"""
这个函数的作用是返回 input 中每行最大的 k 个数，并且返回它们所在位置的索引
input: 一个张量，数据类型必须是以下之一：float32、float64、int32、int64、uint8、int16、int8。数据维度是 batch_size 乘上 x 个类别。
k: 一个整型，必须 >= 1。在每行中，查找最大的 k 个值。
name: 为这个操作取个名字
"""
eg:
input = tf.constant(np.random.rand(3,4))
k = 2
output,index = tf.nn.top_k(input, k)
#返回两个值，一个是top_n,以及对应的索引
with tf.Session() as sess:
    print(sess.run(input))
    print("top_n",sess.run(output))
    print("index",sess.run(index))
"""
output:
[[0.29536964 0.62599181 0.37655239 0.17030019]
 [0.7591839  0.55712539 0.66615823 0.41752363]
 [0.24750249 0.71353945 0.72818269 0.79565611]]
top_n [[0.62599181 0.37655239]
 [0.7591839  0.66615823]
 [0.79565611 0.72818269]]
index [[1 2]
 [0 2]
 [3 2]]
"""
```

# tf.Dataset

## 获取数据

Dataset是存储Tensor结构的类，它可以保存一批Tensor结构，以供模型来训练或者测试。这里，Tensor结构是自己定义的，可以有多种格式。

`tf.data.Dataset.from_tensor_slices()`

这个接口允许我们传递一个或多个Tensor结构给Dataset，因为默认把Tensor的第一个维度作为数据数目的标识，所以要保持数据结构中第一维的一致性.eg:

```python
dataset = tf.data.Dataset.from_tensor_slices(data)
"""
加入传入的数据是一个字典
{“user”:[user_Id,user_Id...............],"item":[item_id,item_id,item_id................],"label":[1,1,1,1,1,0,0,0.......]}

    from_tensor_slices让它变成了:
    {'user': 0, 'item': 1154, 'label': 1}
    {'user': 0, 'item': 3005, 'label': 1}
    {'user': 0, 'item': 2119, 'label': 1}
    {'user': 0, 'item': 1760, 'label': 1}
    {'user': 0, 'item': 1631, 'label': 1}
    {'user': 0, 'item': 260,  'label':  0}
"""
```

这里包含如下信息：
1、该接口可以接受一个字典变量。实际上，该接口接受任何Iterator
2、第一个维度被认为是数据的数量，可以看到，观察数据的shapes的时候，只显示第一维以后的，为什么呢，因为第一维被认为是数据的数量，所以不参与构成shapes

## Dataset的输出方式（迭代器）

### A.make_one_shot_iterator迭代器

```python
dataset = tf.data.Dataset.from_tensor_slices(np.random.randn(10,3))
iterator = dataset.make_one_shot_iterator()
next_element = iterator.get_next()

with tf.Session() as sess:
    for i in range(10):
        value = sess.run(next_element)
        print(i, value)
output:
0 [ 0.78891609  0.31016679 -2.22261044]
1 [ 3.06918115  0.14014906  0.86654045]
2 [ 2.08348332  0.57866576 -0.66946627]
3 [-1.28344434  1.96287407  0.70896466]
4 [-1.28056116 -0.65352575  0.39975416]
5 [-0.70007014 -0.94034185  1.02308444]
6 [ 0.70819506 -0.56918389  0.75509558]
7 [ 0.26925763 -0.18980865 -0.90350774]
8 [ 1.45644465 -1.13308881 -0.37863782]
9 [ 0.4485246  -0.48737583 -0.40142893]
"""
这里，我们先用numpy生成随机数据并储存在Dataset，之后，是用了dataset.make_one_shot_iterator()迭代器来读取数据。one_shot迭代器人如其名，意思就是数据输出一次后就丢弃了。
这就构成了数据进出的一种方式

"""
```

### B.make_initializable_iterator 迭代器

可初始化迭代器允许Dataset中存在占位符，这样可以在数据需要输出的时候，再进行feed操作

```python
max_value = tf.placeholder(tf.int64, shape=[])#占位符
dataset = tf.data.Dataset.range(max_value)
iterator = dataset.make_initializable_iterator()
next_element = iterator.get_next()

with tf.Session() as sess:
# Initialize an iterator over a dataset with 10 elements.
    sess.run(iterator.initializer, feed_dict={max_value: 10})#需要取数据的时候才将需要的参数feed进去
    for i in range(10):
      value = sess.run(next_element)
      assert i == value

    # Initialize the same iterator over a dataset with 100 elements.
    sess.run(iterator.initializer, feed_dict={max_value: 100})#feed了不同的参数
    for i in range(100):
      value = sess.run(next_element)
      assert i == value
```

# tensorflow可视化

`tensorflow`的可视化是使用`summary`和`tensorboard`合作完成的

==注意：sunmary也是一种操作（op）==

