# What&How Backpropagation

# What&How Logistic Regression

这里的Logistic回归其实非常简单.<a src="http://blog.csdn.net/ariessurfer/article/details/41310525">Logistic回归原理</a>中有解释。其实就是用线性调整过的变量输入一个sigmoid函数，通过输出不断地调整线性的参数。这个过程用到了最大似然，最大似然的步骤就是，根据样本和参数计算似然函数，将似然函数对参数求导，当导数为0的时候达到最大似然。常常用对数似然，因为求解方便。


z[1] = wt * x[1] + b; a[1] = sigma(z[1])
what does that means?
![code for Logistic Regression](LogisticRegressioncode.png)

# Why Vectorization
1. vectorization uses parallelization instructions of CPU or GPU.
2. paralleslism help us to do our computations much faster.