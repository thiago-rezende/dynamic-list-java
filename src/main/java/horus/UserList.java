package horus;

import java.util.function.Consumer; 

public class UserList extends DynamicList<User>
{
    public UserList()
    {
        super();
    }

    public void add_user(User user)
    {
        Pair<Boolean, Integer> result = find(user);
        if(result.first()) {
            for (int i = 0; i < user.get_loginList().size(); i++)
                this.get(result.second()).add_login(user.get_loginList().get(i));

            return;
        }

        this.push_back(user);
    }

    public void show_users()
    {
        for(int i = 0; i < this.m_size; i++)
            System.out.println(this.get(i));
    }

    public void show_usersAfterTime(int time)
    {
        for(int i = 0; i < this.m_size; i++)
            for(int j = 0; j < this.get(i).get_loginList().size(); j++)
                if(this.get(i).get_loginList().get(j).get_loginTime() >= time)
                    System.out.println(this.get(i));
    }

    public void show_user(String name)
    {
        boolean found = false;
        for(int i = 0; i < this.m_size; i++)
            if(this.get(i).get_name().equalsIgnoreCase(name))
            {
                System.out.println(this.get(i));
                found = true;
            }
        if(!found)
            System.out.println("User not Found!");
    }

    public void remove_usersBeforeTime(int time)
    {
        int counter = 0;
        for(int i = 0; i < this.m_data.length; i++) {
            if(this.unsafe_get(i) != null)
            {
                for (int j = 0; j < this.unsafe_get(i).get_loginList().size(); j++)
                    if (this.unsafe_get(i).get_loginList().get(j).get_loginTime() >= time)
                        counter++;

                if(counter == 0)
                    this.unsafe_remove(i);

                counter = 0;
            }
            else
                break;
        }

        this.sort();
    }

    public void sortByUsage()
    {
        User aux = null;

        for (int i = 0; i < this.m_size; i++) {

            for (int j = 0; j < this.m_size; j++) {
                User user_a = this.get(i);
                User user_b = this.get(j);

                if(user_b.get_usage() < user_a.get_usage()){
                    aux = user_a;
                    this.m_data[i] = user_b;
                    this.m_data[j] = aux;
                }
            }
        }
    }

    public void sortByName()
    {
        User aux = null;
	
        for (int i = 0; i < this.m_size; i++) {
            
            for (int j = 0; j < this.m_size; j++) {
                User user_a = this.get(i);
                User user_b = this.get(j);
                
                if(Character.toUpperCase(user_b.get_name().charAt(0)) > Character.toUpperCase(user_a.get_name().charAt(0))){
                    aux = user_a;
                    this.m_data[i] = user_b;
                    this.m_data[j] = aux;
                
                }else if(Character.toUpperCase(user_b.get_name().charAt(0)) == Character.toUpperCase(user_a.get_name().charAt(0))){
                    if(Character.toUpperCase(user_b.get_name().charAt(1)) > Character.toUpperCase(user_a.get_name().charAt(1))){
                        aux = user_a;
                        this.m_data[i] = user_b;
                        this.m_data[j] = aux;
                    }	
                    
                }
            }
        }
    }
}