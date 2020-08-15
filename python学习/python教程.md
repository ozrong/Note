# Anaconda常用

## 镜像

```python
conda clean -i  //清除索引缓存，保证用的是镜像站提供的索引。
# 查看当前环境下已安装的包

conda list
# 查看某个指定环境的已安装包
conda list -n python34

# 查找package信息
conda search numpy

# 安装package
conda install -n python34 numpy
# 如果不用-n指定环境名称，则被安装在当前活跃环境
# 也可以通过-c指定通过某个channel安装

# 更新package
conda update -n python34 numpy

# 删除package
conda remove -n python34 numpy

# 更新conda，保持conda最新
conda update conda

# 更新anaconda
conda update anaconda

# 更新python
conda update python
# 假设当前环境是python 3.4, conda会将python升级为3.4.x系列的当前最新版本
```



```python
添加镜像：
conda config --add channels https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/free/win-64/
conda config --add channels https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/main/win-64/
conda config --add channels https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/msys2/win-64/
conda config --add channels https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/mro/win-64/
conda config --add channels https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/pro/win-64/
conda config --add channels https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/r/win-64/
conda config --add channels https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud/numba/label/dev/win-64/
conda config --add channels https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud/pytorch/win-64/
conda config --add channels https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud/msys2/win-64/
conda config --add channels https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud/conda-forge/win-64/
conda config --set show_channel_urls yes
```



## Jupyter Notebook

### 使用虚拟环境

想在Jupyter Notebook里面使用指定的虚拟环境需要在该虚拟环境里面安装`ipykernel`

**注：在你想要使用的那个环境里面运行下面代码**

```python
conda install ipykernel
```

写入 Jupyter 的 kernel

`python -m ipykernel install --user --name 环境名称 --display-name "Python (环境名称)"`

**注：**“环境名称”为当前虚拟环境的名称，最后面引号内的字符串是该虚拟环境显示在 Jupyter Notebook 界面的名字，可以随意修改。

重要：前提你是要在当前环境下安装jupyter

教程：https://xirikm.net/2019/319-1

上面写入 kernel 的配置并不会随虚拟环境的删除而删除。也就是说即使删除了该虚拟环境，Jupyter Notebook 的界面上仍会有它的选项，只是无法正常使用

`jupyter kernelspec remove 环境名称`	

### 代码提示

教程：https://www.jianshu.com/p/0ab80f63af8a

+ 首先安装：`pip install jupyter_contrib_nbextensions`

+ 安装完之后需要配置 nbextension，注意配置的时候要确保**已关闭** Jupyter Notebook

  `jupyter contrib nbextension install --user --skip-running-check`

+ 启动 Jupyter Notebook，勾选设置

​        上面选项栏会出现 Nbextensions 的选项:点开 Nbextensions 的选项，并勾选 Hinterland

# matplotlib

简单教程：https://www.runoob.com/numpy/numpy-matplotlib.html

官方文档：https://matplotlib.org/tutorials/introductory/pyplot.html