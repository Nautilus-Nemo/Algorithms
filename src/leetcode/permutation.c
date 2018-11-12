#include<stdio.h>
#include<stdlib.h>
//STL next permutation ����˼��
int next_permutation(int *begin,int *end);
//STL����permutation���
void all_permutation(int arr[],int n);
//����Ԫ��`����`
void util_swap(int *i_,int *k_);
//ת��`����`
void util_reverse(int *j_, int *end_);
//������
void sift(int r[],int k,int m);
void heapSort(int r[],int n);

int main()
{
	int arr[4] = {1,1,2,3};
	all_permutation(arr,4);
	getchar();
}
//STL next permutation ����˼��
//begin ��ʼ��ַ end ���һ��Ԫ�ص�ַ���ƫ��1
int next_permutation(int *begin,int *end)
{
	int *i = begin,*j,*k;
	//0����һ��Ԫ�أ�û����һ������
	if(i == end || ++i == end) return 0;
	for(i = end - 1;i != begin;)
	{
		//������һ��������(i,j)
		j = i--;
		//�Ӻ���ǰ����ʱ��ִ�����淢��(i,j)�Ĳ������
		if(!(*i < *j)) continue;
		//���ֲ�С��i��k
		for(k = end; !(*i < *(--k)););
		util_swap(i,k);
		//����[j,end)��Χ�ǽ���
		util_reverse(j,end);
		return 1;
	}
	//�����ʼ�ǽ���
	util_reverse(begin,end);
	return 0;
}
//����Ԫ��`����`
void util_swap(int *i,int *k)
{
	int tem = *i;
	*i = *k;
	*k = tem;
}
//ת��`����`
void util_reverse(int *j_, int *end_)
{
	int tem;
	//ƫ��һ����ַ��λ
	int *moveEnd = end_ - 1;
	int *moveJ = j_;
	//��¼��һ��ֵ
	int preMoveJ = *moveEnd;
	//����һ��Ԫ�ص���ȫһ��Ԫ��ֵʱֹͣ
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
//STL����permutation���
void all_permutation(int arr[],int n)
{
	int i;
	heapSort(arr,n - 1);
	do{
		for(i = 1;i < n;printf("%d",arr[i++]));
		printf("\n");
	}while(next_permutation(arr + 1,arr + n));
}

//������
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
			//ֻ�迼�ǵ����Ľ����������������Ϊδ�����ı�����������õ�
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
	while(i >= 1)  //��ʼ���ѣ������һ����֧��������ڵ����
	{
		sift(r,i,n);
		i--;
	}
	while(j > 1) //�ظ�ִ�����߶Ѷ����ؽ��ѵĲ���
	{
		r[0] = r[1];
		r[1] = r[j];
		r[j] = r[0];
		j--;
		sift(r,1,j);

	}
	return;
} 
