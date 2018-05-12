package socialnetwork;

import javafx.scene.chart.*;
import javafx.scene.input.PickResult;

import java.sql.Struct;
import java.text.CollationElementIterator;
import java.util.*;


public class Statistics {

   class object
   {
       int value;
       String name;
       String valuename;
       public object (int x, String y, String z)
       {
           value=x;name=y;valuename=z;
       }
   }
   private Vector<object> most_friends= new Vector<>(5);
   private Vector<object> most_posts= new Vector<>(5);
   private int no_posts=0;
   private int no_users=0;
   private int no_males=0;
   private int no_females=0;
   private int no_singles=0;
   private int no_inarel=0;
   private int no_engaged=0;
   private int no_married=0;
   private Vector<Integer> no_bd= new Vector<>(12);
   private Vector<Integer> friends_range= new Vector<>(6);
   private Vector<object> most_cities;
   private Vector<object> most_bp;
   private Vector<Integer> most_lang = new Vector<>(5); //english french arabic german italian

   PieChart pc_gender = new PieChart();
   PieChart pc_status = new PieChart();
   PieChart pc_lang= new PieChart();
   NumberAxis na_most_friends= new NumberAxis();
   CategoryAxis ca_most_friends = new CategoryAxis();
   BarChart<String, Number> bc_most_friends= new BarChart(ca_most_friends,na_most_friends);
   NumberAxis na_posts = new NumberAxis();
   CategoryAxis ca_posts = new CategoryAxis();
   BarChart<String ,Number> bc_posts = new BarChart(ca_posts,na_posts);
   NumberAxis na_bp = new NumberAxis();
   CategoryAxis ca_bp= new CategoryAxis();
   BarChart<String ,Number> bc_bp = new BarChart(ca_bp,na_bp);
   NumberAxis na_cities = new NumberAxis();
   CategoryAxis ca_cities = new CategoryAxis();
   BarChart<String ,Number> bc_cities = new BarChart(ca_cities,na_cities);
   NumberAxis na_fr= new NumberAxis();
   CategoryAxis ca_fr= new CategoryAxis();
   BarChart<String ,Number> bc_fr = new BarChart(ca_fr,na_fr);
   NumberAxis na_bd = new NumberAxis();
   CategoryAxis ca_bd = new CategoryAxis();
   BarChart<String ,Number> bc_bd = new BarChart(ca_bd,na_bd);

   public Statistics()
   {
       for(int i =0 ;i<12;i++)
       {
           no_bd.add(0);
           if (i<6)
               friends_range.add(0);
           if(i<5)
           {
               most_lang.add(0);
               most_friends.add(new object(0,"",""));
               most_posts.add(new object(0,"",""));
           }
       }
       for(int i=0; i<SocialNetwork.hashTableSize;i++)
       {
           LinkedList<user> l = SocialNetwork.usersHashTable[i];
           if(l!=null)
           {
               for (user u : l)
               {
                   no_users++;
                   no_posts+=u.getPosts().size();
                   if(u.getInfo().getGender()==Informations.Gender.male)
                       no_males++;
                   else no_females++;
                   if(u.getInfo().getStatus()==Informations.MaritalStatus.single)
                       no_singles++;
                   else if(u.getInfo().getStatus()==Informations.MaritalStatus.inarelationship)
                       no_inarel++;
                   else if(u.getInfo().getStatus()==Informations.MaritalStatus.engaged)
                       no_engaged++;
                   else if(u.getInfo().getStatus()==Informations.MaritalStatus.married)
                       no_married++;
                   no_bd.set(u.getInfo().getDateOfBirth().getMonth()-1,no_bd.get(u.getInfo().getDateOfBirth().getMonth()-1)+1);
                   for(String s : u.getInfo().getLanguages())
                   {
                       if(s.equalsIgnoreCase("english"))
                           most_lang.set(0,most_lang.get(0)+1);
                       else if(s.equalsIgnoreCase("french"))
                           most_lang.set(1,most_lang.get(1)+1);
                       else if(s.equalsIgnoreCase("arabic"))
                           most_lang.set(2,most_lang.get(2)+1);
                       else if(s.equalsIgnoreCase("german"))
                           most_lang.set(3,most_lang.get(3)+1);
                       else if(s.equalsIgnoreCase("italian"))
                           most_lang.set(4,most_lang.get(4)+1);
                   }
                   if(u.getFriends_names().size()<=10)
                       friends_range.set(0,friends_range.get(0)+1);
                   else if(u.getFriends_names().size()>10 && u.getFriends_names().size()<=20 )
                       friends_range.set(1,friends_range.get(1)+1);
                   else if(u.getFriends_names().size()>20 && u.getFriends_names().size()<=30 )
                       friends_range.set(2,friends_range.get(2)+1);
                   else if(u.getFriends_names().size()>30 && u.getFriends_names().size()<=40 )
                       friends_range.set(3,friends_range.get(3)+1);
                   else if(u.getFriends_names().size()>40 && u.getFriends_names().size()<=50 )
                       friends_range.set(4,friends_range.get(4)+1);
                   else if(u.getFriends_names().size()>50 && u.getFriends_names().size()<=60 )
                       friends_range.set(5,friends_range.get(5)+1);
               }
           }
       }
       int init_count=0;
       for(int i=0; i<SocialNetwork.hashTableSize;i++)
       {
           LinkedList<user> l = SocialNetwork.usersHashTable[i];
           if(l!=null)
           {
               for (user u : l)
               {
                   if(init_count<5 ) {
                       most_friends.get(init_count).value = u.getFriends_names().size();
                       most_friends.get(init_count).name = u.getUsername();
                       init_count++;
                       if(init_count==5)
                       {
                          // Comparator comparator = Collections.reverseOrder();
                           //Collections.sort(most_friends,comparator );
                       }

                   }
                   else
                   {
                        if(u.getFriends_names().size()>most_friends.get(4).value)
                        {
                            most_friends.set(4,new object( u.getFriends_names().size(), u.getUsername(),""));
                            Comparator comparator = Collections.reverseOrder();
                            Collections.sort(most_friends,comparator );
                        }
                   }
               }
           }
       }
       init_count=0;
       for(int i=0; i<SocialNetwork.hashTableSize;i++)
       {
           LinkedList<user> l = SocialNetwork.usersHashTable[i];
           if(l!=null)
           {
               for (user u : l)
               {
                   if(init_count<5 ) {
                       most_posts.get(init_count).value = u.getPosts().size();
                       most_posts.get(init_count).name = u.getUsername();
                       init_count++;
                       if(init_count==5)
                       {
                           //Comparator comparator = Collections.reverseOrder();
                           //Collections.sort(most_posts,comparator );
                       }
                   }
                   else
                   {
                       if(u.getPosts().size()>most_posts.get(4).value)
                       {
                           most_posts.set(4,new object( u.getPosts().size(), u.getUsername(),""));
                           Comparator comparator = Collections.reverseOrder();
                           Collections.sort(most_posts,comparator );
                       }
                   }
               }
           }
       }
       init_count=0;
       Vector<String> cities1 = new Vector<>(60);
       most_cities = new Vector<>(60);
       for(int i=0; i<SocialNetwork.hashTableSize;i++)
       {
           LinkedList<user> l = SocialNetwork.usersHashTable[i];
           if(l!=null)
           {
               for (user u : l)
               {
                   cities1.add(u.getInfo().getCity());
                   init_count++;
               }
           }
       }
       for(int i=0;i<cities1.size();i++)
       {
           most_cities.add(new object (1,"",cities1.get(i)));
           for(int j=i+1;j<cities1.size();j++)
           {
               if(most_cities.get(i).valuename.equalsIgnoreCase(cities1.get(j)))
               {
                   most_cities.get(i).value++;
                   cities1.remove(j);
                   j--;
               }
           }
       }
       for (int i=5;i<most_cities.size();i++)
           most_cities.remove(i);
  //     Comparator comparator = Collections.reverseOrder();
//       Collections.sort(most_cities,comparator );
       cities1.removeAllElements();

       init_count=0;
       Vector<String> bp1 = new Vector<>(60);
       most_bp = new Vector<>(60);
       for(int i=0; i<SocialNetwork.hashTableSize;i++)
       {
           LinkedList<user> l = SocialNetwork.usersHashTable[i];
           if(l!=null)
           {
               for (user u : l)
               {
                   bp1.add(u.getInfo().getBirth_place());
                   init_count++;
               }
           }
       }
       for(int i=0;i<bp1.size();i++)
       {
           most_bp.add(new object (1,"",bp1.get(i)));
           for(int j=i+1;j<bp1.size();j++)
           {
               if(most_bp.get(i).valuename.equalsIgnoreCase(bp1.get(j)))
               {
                   most_bp.get(i).value++;
                   bp1.remove(j);
                   j--;
               }
           }
       }
       for (int i=5;i<most_bp.size();i++)
           most_bp.remove(i);
       //Comparator comparatorq = Collections.reverseOrder();
       //Collections.sort(most_bp,comparatorq );
       //////////////////////////////////////////////
       pc_gender.getData().addAll(new PieChart.Data(String.valueOf(100*(double)no_males/((double)no_females+(double)no_males)).substring(0,4)
                       +"% Male" , no_males),
               new PieChart.Data(String.valueOf(100*(double)no_females/((double)no_females+(double)no_males)).substring(0,4)+"% Female",
                       no_females));
       double total_stats= no_engaged+no_married+no_singles+no_inarel;
       pc_status.getData().addAll(new PieChart.Data(String.valueOf(100*(double)no_singles/total_stats).substring(0,4)+"% Single",no_singles)
               ,new PieChart.Data(String.valueOf(100*(double)no_inarel/total_stats).substring(0,4)+"% In A Relation",no_inarel),
               new PieChart.Data(String.valueOf(100*(double)no_engaged/total_stats).substring(0,4)+"% Engaged",no_engaged),
               new PieChart.Data(String.valueOf(100*(double)no_married/total_stats).substring(0,4)+ "% Married",no_married));
       pc_lang.getData().addAll(new PieChart.Data("English",most_lang.get(0)),
               new PieChart.Data("French",most_lang.get(1)),new PieChart.Data("Arabic",most_lang.get(2)),
               new PieChart.Data("German",most_lang.get(3)),new PieChart.Data("Italian",most_lang.get(4)));
       XYChart.Series<String, Number> series1 = new XYChart.Series<>();
       bc_cities.setTitle("Top 5 Cities");
       for(object o:most_cities)
       {
           series1.getData().add(new XYChart.Data<>(o.valuename,o.value));
       }
       bc_cities.getData().add(series1);
       bc_cities.setLegendVisible(false);

       XYChart.Series<String, Number> series2 = new XYChart.Series<>();
       bc_bp.setTitle("Top 5 Birth Places");
       for(object o:most_bp)
       {
           series2.getData().add(new XYChart.Data<>(o.valuename,o.value));
       }
       bc_bp.getData().add(series2);
       bc_bp.setLegendVisible(false);

       XYChart.Series<String, Number> series3 = new XYChart.Series<>();
       bc_most_friends.setTitle("Top 5 Users With Highest Number of Friends");
       for(object o:most_friends)
       {
           series3.getData().add(new XYChart.Data<>(o.name,o.value));
       }
       bc_most_friends.getData().add(series3);
       bc_most_friends.setLegendVisible(false);

       XYChart.Series<String, Number> series4 = new XYChart.Series<>();
       bc_posts.setTitle("Top 5 Users With Highest Number of Posts\n             Number of All Posts: "+String.valueOf(no_posts));
       for(object o:most_posts)
       {
           series4.getData().add(new XYChart.Data<>(o.name,o.value));
       }
       bc_posts.getData().add(series4);
       bc_posts.setLegendVisible(false);

       XYChart.Series<String, Number> series5 = new XYChart.Series<>();
       bc_bd.setTitle("Users Birthdays");
       series5.getData().addAll(new XYChart.Data<>("January",no_bd.get(0)),new XYChart.Data<>("February",no_bd.get(1)),
               new XYChart.Data<>("March",no_bd.get(2)),new XYChart.Data<>("April",no_bd.get(3)),
               new XYChart.Data<>("May",no_bd.get(4)),new XYChart.Data<>("June",no_bd.get(5)),
               new XYChart.Data<>("July",no_bd.get(6)),new XYChart.Data<>("August",no_bd.get(7)),
               new XYChart.Data<>("September",no_bd.get(8)),new XYChart.Data<>("October",no_bd.get(9)),
               new XYChart.Data<>("November",no_bd.get(10)),new XYChart.Data<>("December",no_bd.get(11)));
       bc_bd.getData().add(series5);
       bc_bd.setLegendVisible(false);
       bc_bd.setBarGap(0);

       XYChart.Series<String, Number> series6 = new XYChart.Series<>();
       bc_fr.setTitle("Users' Number of Friends\n(Number of All Users: "+String.valueOf(no_users)+")");
       series6.getData().addAll(new XYChart.Data<>("0-10 Friends",friends_range.get(0)),
               new XYChart.Data<>("11-20 Friends",friends_range.get(1)),
               new XYChart.Data<>("21-30 Friends",friends_range.get(2)),
               new XYChart.Data<>("31-40 Friends",friends_range.get(3)),
               new XYChart.Data<>("41-50 Friends",friends_range.get(4)),
               new XYChart.Data<>("51-60 Friends",friends_range.get(5)));
       bc_fr.getData().add(series6);
       bc_fr.setBarGap(0);
       bc_fr.setLegendVisible(false);
   }

    public BarChart<String, Number> getBc_fr() {
        return bc_fr;
    }

    public BarChart<String, Number> getBc_bd() {
        return bc_bd;
    }

    public BarChart<String, Number> getBc_posts() {
        return bc_posts;
    }

    public BarChart<String, Number> getBc_most_friends() {
        return bc_most_friends;
    }

    public BarChart<String, Number> getBc_cities() {
        return bc_cities;
    }

    public Vector<object> getMost_cities() {
        return most_cities;
    }

    public Vector<object> getMost_bp() {
        return most_bp;
    }

    public BarChart<String, Number> getBc_bp() {
        return bc_bp;
    }

    public PieChart getPc_gender() {
        return pc_gender;
    }

    public PieChart getPc_status() {
        return pc_status;
    }

    public PieChart getPc_lang() {
        return pc_lang;
    }
}
