package javasourcesnippet;

public class StringSnippet {
    private char[] value;
    public int lastIndexOf(StringSnippet str,int fromIndex){
        return lastIndexOf(value,0,value.length,
                str.value,0,str.value.length,fromIndex);
    }
    //sourceOffest是在Strings中废弃的但占用的字符数组元素个数
    public int lastIndexOf(char[] source,int sourceOffest,int sourceCount,
                           char[] target,int targetOffest,int targetCount,
                           int fromIndex){
        //1.最大索引的可能值
        //2.注意数组从0开始，长度减一
        int rightIndex = sourceCount-targetCount;
        //3.参数合法性检验
        if(fromIndex < 0){
            return -1;
        }
        if(fromIndex > rightIndex){
            fromIndex=rightIndex;
        }
        /* Empty string always matches. */
        if(targetCount == 0){
            return fromIndex;
        }

        //4.记录目标字符串最后一个字符索引处和该字符内容
        int strLastIndex = targetOffest + targetCount - 1;
        char strLastChar = target[strLastIndex];
        //5.只需要遍历到min处即可停止遍历，因为在min前面的字符数量已经小于目标字符串的长度
        int min = sourceOffest + targetCount - 1;
        //6.strLastChar在source中的最大索引
        int i = min + fromIndex;
    startSearchForLastChar:
        while (true){
            //7.在有效遍历区间内，循环查找第一个与目标字符串最后一个字符相等的字符，如果找到，则跳出循环，该字符的索引是i
            while (i >= min && source[i] != strLastChar){
                i--;
            }
            //8.如果已经小于min，那么说明没找到，直接返回-1
            if(i < min){
                return -1;
            }
            //9.找到，则再进行查找目标字符串除去最后一个字符剩下的字串
            int j = i - 1;
            //10.目标字符串除去最后一个字符剩下的字串长度是targetCount - 1,此处start是此次剩余字串查找能到达的最小索引
            int start = j - (targetCount - 1);
            //11.记录目标字符串的倒数第二个字符所在target中的索引
            int k = strLastChar - 1;

            //循环查找剩余字串是否全部字符相同
            //不相同则直接跳出继续第六步
            //全部相同则返回索引
            while(j > start){
                if(source[j--] != target[k--]){
                    i--;
                    continue startSearchForLastChar;
                }
            }
            return start - sourceOffest + 1;
        }
    }
    public static void main(String[] args){
        StringSnippet stringSnippet=new StringSnippet();
        char[] source={'a','b','c','d','e','f'};
        stringSnippet.value=source;
        StringSnippet stringSnippet1=new StringSnippet();
        char[] source1={'d','e','f'};
        stringSnippet1.value=source1;
        int i=stringSnippet.lastIndexOf(stringSnippet1,0);
        System.out.println(i);
    }
}
