#include<stdio.h>
#include<stdlib.h>
//STL next permutation 基本思想
int next_permutation(int *begin,int *end);
//STL所有permutation结果
void all_permutation(int arr[],int n);
//交换元素`引用`
void util_swap(int *i_,int *k_);
//转置`引用`
void util_reverse(int *j_, int *end_);
//堆排序
void sift(int r[],int k,int m);
void heapSort(int r[],int n);

int main()
{
	int arr[4] = {1,1,2,3};
	all_permutation(arr,4);
	getchar();
}
//STL next permutation 基本思想
//begin 起始地址 end 最后一个元素地址向后偏移1
int next_permutation(int *begin,int *end)
{
	int *i = begin,*j,*k;
	//0或者一个元素，没有下一个排列
	if(i == end || ++i == end) return 0;
	for(i = end - 1;i != begin;)
	{
		//发现下一个增长对(i,j)
		j = i--;
		//从后往前升序时不执行下面发现(i,j)的操作语句
		if(!(*i < *j)) continue;
		//发现不小于i的k
		for(k = end; !(*i < *(--k)););
		util_swap(i,k);
		//现在[j,end)范围是降序
		util_reverse(j,end);
		return 1;
	}
	//如果开始是降序
	util_reverse(begin,end);
	return 0;
}
//交换元素`引用`
void util_swap(int *i,int *k)
{
	int tem = *i;
	*i = *k;
	*k = tem;
}
//转置`引用`
void util_reverse(int *j_, int *end_)
{
	int tem;
	//偏移一个地址单位
	int *moveEnd = end_ - 1;
	int *moveJ = j_;
	//记录上一个值
	int preMoveJ = *moveEnd;
	//当后一个元素等于全一个元素值时停止
	while(*moveJ != preMoveJ && *moveJ != *moveEnd)
	{
		preMoveJ = *moveJ;
		tem = *moveJ;
		*moveJ = *moveEnd;
		*moveEnd = tem;
		++moveJ;
		--moveEnd;
	}
}
//STL所有permutation结果
void all_permutation(int arr[],int n)
{
	int i;
	heapSort(arr,n - 1);
	do{
		for(i = 1;i < n;printf("%d",arr[i++]));
		printf("\n");
	}while(next_permutation(arr + 1,arr + n));
}

//堆排序
void sift(int r[],int k,int m)
{
	int i,j;
	i=k;
	j=2 * i;
	while(j <= m)
	{
		if(j < m && r[j] < r[j+1])
			j++;
		if(r[i] > r[j])
			break;
		else
		{
			int tem = r[j];
		    r[j] = r[i];
			r[i] = tem;
			//只需考虑调换的进行推排序调正，因为未调换的本来就是排序好的
			i = j;
			j = 2 * i;
		}
	}
	return;
}

void heapSort(int r[],int n)
{
	int j = n;
	int i = n / 2;  
	while(i >= 1)  //初始建堆，从最后一个分支结点往根节点调整
	{
		sift(r,i,n);
		i--;
	}
	while(j > 1) //重复执行移走堆顶及重建堆的操作
	{
		r[0] = r[1];
		r[1] = r[j];
		r[j] = r[0];
		j--;
		sift(r,1,j);

	}
	return;
} 
