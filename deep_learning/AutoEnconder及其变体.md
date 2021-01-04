# 自动编码器AutoEnconder

自动编码器神经网络是一种**无监督机器学习算法**、有三层的神经网络：输入层、隐藏层（编码层）和输出层（解码层）。

自动编码器的基本结构如图1所示，包括编码和解码两个过程：

![img](https://pic4.zhimg.com/80/v2-bcef9c051ce34d8d63721690b4adc53b_720w.jpg)

自动编码器是将输入 ![[公式]](https://www.zhihu.com/equation?tex=x) 进行编码，得到新的特征 ![[公式]](https://www.zhihu.com/equation?tex=y) ，并且希望原始的输入 ![[公式]](https://www.zhihu.com/equation?tex=x) 能够从新的特征 ![[公式]](https://www.zhihu.com/equation?tex=y) 重构出来。编码过程如下：
$$
y = f(Wx+b)
$$
可以看到，和神经网络结构一样，其编码就是线性组合之后加上非线性的激活函数。如果没有非线性的包装，那么自动编码器就和普通的PCA没有本质区别了。利用新的特征 ![[公式]](https://www.zhihu.com/equation?tex=y) ，可以对输入 ![[公式]](https://www.zhihu.com/equation?tex=x) 重构，即解码过程：

![[公式]](https://www.zhihu.com/equation?tex=x%27%3Df%28W%27x%2Bb%27%29)





# 堆栈自动编码器(Stacked Autoencoder)

前面我们讲了自动编码器的原理，不过所展示的自动编码器只是简答的含有一层，其实可以采用更深层的架构，这就是堆栈自动编码器或者深度自动编码器，本质上就是增加中间特征层数。

![img](https://pic3.zhimg.com/80/v2-29567c3d74b9fa414b5d71c439febbee_720w.jpg)

# 去噪自动编码器(Denoising Autoencoder)

# 稀疏自动编码器(Sparse Autoencoder)

# 变分自动编码器(Variational Auto-Encoders，VAE)

