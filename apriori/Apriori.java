package apriori;
import java.io.*;
import java.util.*;
class Apriori
{
	public static void main(String args[])throws Exception
	{
            String data[] = {"1. Cleo", "2. Aqua", "3. Vit", "4. CLUB"
            , "5. Equil"};
            System.out.println("Data :");
            for (int i = 0; i < data.length; i++) {
                System.out.println(data[i]);
            }
		int n,minSupport;
		int max=0;
		int maxLen=0;
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		Apriori ap=new Apriori();
		System.out.print("Masukkan Jumlah Transaksi : ");
		n=Integer.parseInt(in.readLine());
                System.out.println("Transaksi : ");
		String trans[]=new String[n];
		for(int i=0;i<n;i++)
		{
			trans[i]=in.readLine();
			for(int j=0;j<trans[i].length();j++)
			{
				if(Integer.parseInt(""+trans[i].charAt(j))>max)
					max=Integer.parseInt(""+trans[i].charAt(j));
			}
		}
		System.out.print("Masukkan Minimum Support : ");
		minSupport=Integer.parseInt(in.readLine());
		String str="";
		for(int i=1;i<=max;i++)
			str+=""+i;
		ArrayList al;
                
		max=0;
		ArrayList<String> allItems=new ArrayList<String>();
		for(int i=1;i<=str.length();i++)
		{
			al=ap.hitungTransaksi(str.substring(0,i));
                        
			for(int j=0;j<al.size();j++)
			{
				allItems.add(""+al.get(j));
				if(new String(""+al.get(j)).length()>max)
					max=new String(""+al.get(j)).length();       
			}
		}
		ArrayList<String> arr[]=new ArrayList[max];
		for(int i=0;i<max;i++)
			arr[i]=new ArrayList<String>();
		for(int j=0;j<allItems.size();j++){
                    
			arr[( allItems.get(j).length())-1].add(allItems.get(j));
                        
                }
                
		int count[]=new int[allItems.size()],l=0;
		int index=0;
		for(int i=0;i<max;i++)
		{
			for(int j=0;j<arr[i].size();j++)
			{
                            
				for(int k=0;k<n;k++)
				{
					for(int m=0;m<(arr[i].get(j).length());m++)
					{
                                            
						if(trans[k].indexOf(arr[i].get(j).charAt(m))==-1){
                                                    
							index=1;
                                                }
					}
					if(index==0){
						count[l]+=1;
                                        }
					index=0;
				}
				l++;
			}
		}
                
		int k=0;
		for(int i=0;i<max;i++)
		{
			for(int j=0;j<arr[i].size();j++)
			{
				if(count[k]>=minSupport)
				{
					if(maxLen<arr[i].get(j).length()){
						maxLen=arr[i].get(j).length();                                             
                                        }
				}
				k++;
			}
		}
                
		k=0;
                System.out.println("ItemSet | Sup");

		for(int i=0;i<max;i++)
		{
			for(int j=0;j<arr[i].size();j++)
			{
                            if(count[k]>=minSupport)
                            {
                                System.out.println(arr[i].get(j)+" | "+count[k]);
                            }
                            k++;                            
			}
                        System.out.println("=================");
		}                              
	}
	ArrayList<String> hitungTransaksi(String str)
	{
		String temp="",match="";
		int flag=0;
		ArrayList<String> result=new ArrayList<String>();
		if(str.length()==1)
		{
			result.add(str);                                           
			return result;
		}
		else
		{
			char ch=str.charAt(0);
			String rem=str.substring(1);
			ArrayList<String> a=hitungTransaksi(rem);
			for(String p:a)
			{
				result.add(p);
				ArrayList addn=insertItems(ch,p);
				for(int i=0;i<addn.size();i++)
				{
					match=(String)addn.get(i);
					flag=0;
					for(int j=i+1;j<addn.size();j++)
					{
						temp=(String)addn.get(j);
						for(int k=0;k<temp.length();k++)
						{
							for(int l=0;l<match.length();l++)
							{
								if(temp.charAt(k)==match.charAt(l)){
									flag++;
                                                                }
							}
						}
						if(flag>=match.length())
							addn.remove(j);
					}
				}
				result.addAll(addn);
			}                       
                        
			return(result);
                        
		}
	}
	ArrayList<String> insertItems(char c,String str)
	{
		String temp="",match="";
		int flag=0;
		ArrayList<String> res=new ArrayList<String>();
		for(int i=0;i<=str.length();i++)
		{
			res.add(str.substring(0,i)+c+str.substring(i));
		}
		for(int i=0;i<res.size();i++)
		{
			match=(String)res.get(i);
			flag=0;
			for(int j=i+1;j<res.size();j++)
			{
				temp=(String)res.get(j);
				for(int k=0;k<temp.length();k++)
				{
					for(int l=0;l<match.length();l++)
					{
						if(temp.charAt(k)==match.charAt(l))
							flag++;                                               
					}
				}
				if(flag>=match.length())
					res.remove(j);
			}
		}
		return(res);
	}
}
