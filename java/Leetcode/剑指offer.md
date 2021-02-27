# [剑指 Offer 29. 顺时针打印矩阵](https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/)

输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。

 

示例 1：

输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[1,2,3,6,9,8,7,4,5]

示例 2：

输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
输出：[1,2,3,4,8,12,11,10,9,5,6,7]

```java
答案：
class Solution {
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int rows = matrix.length, columns = matrix[0].length;
        int[] order = new int[rows * columns];
        int index = 0;
        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;
        while (left <= right && top <= bottom) {
            for (int column = left; column <= right; column++) {
                order[index++] = matrix[top][column];
            }
            for (int row = top + 1; row <= bottom; row++) {
                order[index++] = matrix[row][right];
            }
            if (left < right && top < bottom) {
                for (int column = right - 1; column > left; column--) {
                    order[index++] = matrix[bottom][column];
                }
                for (int row = bottom; row > top; row--) {
                    order[index++] = matrix[row][left];
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }
}




自己敲得
class Solution {
    public int[] spiralOrder(int[][] matrix) {
        int len = matrix.length;
      
        if(matrix==null || len==0){
            return new int[0];
        }
        int wide = matrix[0].length;
        int[] ans = new int[len*wide];
        int index = 0;
        int left = 0,right = wide-1,top = 0,bottom = len-1;
        while(left<=right && top<=bottom ){  可以理解为4个指针  上  下  左  右
            

        for(int j=left;j<=right;j++ ){     左->右
            ans[index] = matrix[top][j];
            index++;
        }
        for(int i = top+1;i<=bottom;i++ ){ 上->下
            ans[index] = matrix[i][right];
            index++;
        }

        if(left < right && top < bottom){  //为什么需要这个判断尼？？？？？
                                          
            
            /*
            答：
            例如：
            1  2  3  4
            5  6  7  8
            9  10 11 12
            
            
            本来ans这个数组的长度就是12  索引到11。  但是在在第二次遍历内圈的时候
            出现了  left=1 right=2 top=bottom=1 的情况，如果执行 左->右 的操作这会在读一个6到ans中  但是这时其实ans已经装满了
            所以数组越界了
            
            
            
            这是不要这个判断，并增加ans的长度得到的答案
            [1,2,3,4,8,12,11,10,9,5,6,7,6,0,0,0,0,0,0,0,0,0]
            显然重复读了一次6
            */
            

            for(int j = right-1;j>=left; j--){  右—>左
                ans[index] = matrix[bottom][j];
                index++;
            }
            for(int i=bottom-1;i>top;i--){  下->上
                ans[index] = matrix[i][left];
                index++;
            }
    
        }
        left++;
        right--;
        top++;
        bottom--;
        }

        return ans;
       
    }
}
```

