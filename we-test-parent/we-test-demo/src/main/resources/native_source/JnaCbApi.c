#include <stdio.h>
//定义回调函数指针
static int (*AddByCallback)(int a,int b)=NULL;
//注册回调函数
void RegisterAdd(int (*callback)(int,int))
{
	AddByCallback=callback;
}
//执行回调函数
void DoAddByCallback(int a,int b)
{
	int res;
	if(AddByCallback==NULL)
	{
		printf("error: please register the Add function first!\n");
		return;
	}
	res=AddByCallback(a,b);
	printf("Java execute add: %d+%d=%d\n",a,b,res);
}