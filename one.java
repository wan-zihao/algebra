//求next数组(i == 1时就只能从0开始)(自己匹配自己，每次循环有点像dp)
        for(int i = 2, j = 0; i <= n; i ++){
            //如果一直匹配不到就一直回退，知道匹配成功或者回退到0
            while(j != 0 && p[j + 1] != p[i]){
                j = ne[j];
            }
            //如果匹配成功,j就加一（因为j是错了一位的，加上1才应该是回退的点，回退的这个点是匹配成功的最后一个字符，由于错了一位，之后回退后，再次匹配就刚好是从这个最后一位成功的下一位开始匹配判断）
            //如果不成功,则说明j已经等于0了,后面给next赋值时就是赋0，说明这一位回退就意味着重新匹配
            if(p[j + 1] == p[i]){
                j ++;
            }
            //将j赋值给对应next
            ne[i] = j;
        }

//kmp匹配过程
//注意：ss为长串,i为长串匹配时的下标（用数组存的,下标从1开始）,m是长串长度
//注意：ps为短串,j为长串匹配时的下标（用数组存的,下标从1开始）（但是初始化为0，每次比较p[j + 1] 和s[i]）
        for(int i = 1, j = 0; i <= m; i ++){
            //如果j没有回退到起点，而且后面一位的匹配失败，那么就回退
            //直到匹配成功或者回退到原点（重新开始匹配）
            while(j != 0 && p[j + 1] != s[i]){
                j = ne[j];
            }
            //如果匹配成功就直接j++,由于每次循环i都会自增，所以此时不需要i++
            if(p[j + 1] == s[i]){
                j ++;
            }
            if(j == n){
                //匹配成功，输出(输出结果这里下标从0开始，看每道题的要求)
                System.out.print(i - n + "");
                //回退，寻找下一个匹配的点
                j = ne[j];
            }
        }
