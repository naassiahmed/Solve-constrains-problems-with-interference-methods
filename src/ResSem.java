import java.util.ArrayList;

public class ResSem {
	
	String[][] data;
	
	public ResSem(String[][] data)
	{
		this.data=new String[data[0].length][data[0].length];
		for (int i=0;i<data[0].length;i++)
		{
			System.arraycopy(data[i], 0, this.data[i], 0, data[i].length);
		}
	}
	
	public boolean contains(String s)
	{
		for (int i=0;i<data[0].length;i++)
		{
			for (int j=0;j<data[0].length;j++)
			{
				if(data[i][j].equals(s)) return true;
			}
		}
		return false;
	}
	
	public int geti(String s)
	{
		int i=1;
		while (i<data[0].length && !data[i][0].equals(s)) i++;
		return i;
	}
	
	public int getj(String s)
	{
		int j=1;
		while (j<data[0].length && !data[j][0].equals(s)) j++;
		return j;
	}
	
	public boolean checkLink(String n1, String lien, String n2)
	{
		if (data[geti(n1)][getj(n2)].equals(lien)) return true;
		return false;
	}
	
	public String propagation1(String n1, String lien, String n2)
	{
		if(n1.equals("") || n2.equals("")) return "Erreur : noeud manquant";
		if(lien.equals("")) return "Erreur : lien manquant";
		if(n1.equals(n2)) return "Erreur : les deux noeuds doivent être différents";
		if(!contains(n1)) return "Erreur : le noeud 1 n'existe pas dans le réseau";
		if(!contains(n2)) return "Erreur : le noeud 2 n'existe pas dans le réseau";
		if(!contains(lien)) return "Erreur : le lien n'existe pas dans le réseau";
		if(checkLink(n1,lien,n2)) return "Solution trouvée : "+n1+" "+lien+" "+n2;
		ArrayList<String> old1 = new ArrayList<String>();
		ArrayList<String> new1 = new ArrayList<String>();
		ArrayList<String> old2 = new ArrayList<String>();
		ArrayList<String> new2 = new ArrayList<String>();
		old1.add(n1);
		old2.add(n2);
		for(int i=1;i<data[0].length;i++) if (data[i][getj(n1)].equals("is-a")) new1.add(data[i][0]);
		for(int i=0;i<new1.size();i++) System.out.println(new1.get(i)); System.out.println();
		for(int i=1;i<data[0].length;i++) if (data[i][getj(n2)].equals("is-a")) new2.add(data[i][0]);
		for(int i=0;i<new2.size();i++) System.out.println(new2.get(i)); System.out.println();
		boolean b=true;
		while (b==true)
		{
			for(int i=0;i<old1.size();i++)
			{
				for(int j=0;j<new2.size();j++) if(checkLink(old1.get(i),lien,new2.get(j))) return "Solution trouvée : "+old1.get(i)+" "+lien+" "+new2.get(j);
			}
			for(int i=0;i<new1.size();i++)
			{
				for(int j=0;j<old2.size();j++) if(checkLink(new1.get(i),lien,old2.get(j))) return "Solution trouvée : "+new1.get(i)+" "+lien+" "+old2.get(j);
				for(int j=0;j<new2.size();j++) if(checkLink(new1.get(i),lien,new2.get(j))) return "Solution trouvée : "+new1.get(i)+" "+lien+" "+new2.get(j);
			}
			
			b=false;
			int next=new1.size();
			for(int i=0;i<next;i++)
			{
				for(int j=1;j<data[0].length;j++) if (data[j][getj(new1.get(i))].equals("is-a")) new1.add(data[j][0]);
			}
			for(int i=0;i<next;i++)
			{
				old1.add(new1.get(0)); new1.remove(0);
			}
			for(int i=0;i<new1.size();i++) System.out.println(new1.get(i)); System.out.println();
			
			next=new2.size();
			for(int i=0;i<next;i++)
			{
				for(int j=1;j<data[0].length;j++) if (data[j][getj(new2.get(i))].equals("is-a")) new2.add(data[j][0]);
			}
			for(int i=0;i<next;i++)
			{
				old2.add(new2.get(0)); new2.remove(0);
			}
			for(int i=0;i<new2.size();i++) System.out.println(new2.get(i)); System.out.println();
			if (new1.size()>0 || new2.size()>0) b=true;
		}
		return "Aucune solution trouvée";
	}
	
	public String propagation2(String n1, String lien, String n2)
	{
		String sol="";
		if(n1.equals("") || n2.equals("")) return "Erreur : noeud manquant";
		if(lien.equals("")) return "Erreur : lien manquant";
		if(n1.equals(n2)) return "Erreur : les deux noeuds doivent être différents";
		if(!contains(n1)) return "Erreur : le noeud 1 n'existe pas dans le réseau";
		if(!contains(n2)) return "Erreur : le noeud 2 n'existe pas dans le réseau";
		if(!contains(lien)) return "Erreur : le lien n'existe pas dans le réseau";
		if(checkLink(n1,lien,n2)) sol=sol+n1+" "+lien+" "+n2;
		ArrayList<String> old1 = new ArrayList<String>();
		ArrayList<String> new1 = new ArrayList<String>();
		ArrayList<String> old2 = new ArrayList<String>();
		ArrayList<String> new2 = new ArrayList<String>();
		old1.add(n1);
		old2.add(n2);
		for(int i=1;i<data[0].length;i++) if (data[i][getj(n1)].equals("is-a")) new1.add(data[i][0]);
		for(int i=0;i<new1.size();i++) System.out.println(new1.get(i)); System.out.println();
		for(int i=1;i<data[0].length;i++) if (data[i][getj(n2)].equals("is-a")) new2.add(data[i][0]);
		for(int i=0;i<new2.size();i++) System.out.println(new2.get(i)); System.out.println();
		boolean b=true;
		while (b==true)
		{
			for(int i=0;i<old1.size();i++)
			{
				for(int j=0;j<new2.size();j++) if(checkLink(old1.get(i),lien,new2.get(j))) sol=sol+old1.get(i)+" "+lien+" "+new2.get(j)+"\n";
			}
			for(int i=0;i<new1.size();i++)
			{
				for(int j=0;j<old2.size();j++) if(checkLink(new1.get(i),lien,old2.get(j))) sol=sol+new1.get(i)+" "+lien+" "+old2.get(j)+"\n";
				for(int j=0;j<new2.size();j++) if(checkLink(new1.get(i),lien,new2.get(j))) sol=sol+new1.get(i)+" "+lien+" "+new2.get(j)+"\n";
			}
			
			b=false;
			int next=new1.size();
			for(int i=0;i<next;i++)
			{
				for(int j=1;j<data[0].length;j++) if (data[j][getj(new1.get(i))].equals("is-a")) new1.add(data[j][0]);
			}
			for(int i=0;i<next;i++)
			{
				old1.add(new1.get(0)); new1.remove(0);
			}
			for(int i=0;i<new1.size();i++) System.out.println(new1.get(i)); System.out.println();
			
			next=new2.size();
			for(int i=0;i<next;i++)
			{
				for(int j=1;j<data[0].length;j++) if (data[j][getj(new2.get(i))].equals("is-a")) new2.add(data[j][0]);
			}
			for(int i=0;i<next;i++)
			{
				old2.add(new2.get(0)); new2.remove(0);
			}
			for(int i=0;i<new2.size();i++) System.out.println(new2.get(i)); System.out.println();
			if (new1.size()>0 || new2.size()>0) b=true;
		}
		if(sol.equals(" ")) return "Aucune solution trouvée";
		else return "Solution(s) trouvée(s) :\n"+sol;
	}

}
