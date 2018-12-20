/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package course;

import java.util.ArrayList;

/**
 *
 * @author wizard
 */
public class AppModel implements Model{
    private ArrayList<View> views = null;
    private Object data = null;
    
    public AppModel(){
        this.views = new ArrayList<>();
    }
    
    @Override
    public void registerView(View view) {
        if( view != null){
            this.views.add(view); 
        }
    }

    @Override
    public void setData(Object data) {
        if( data != null && !data.equals(this.data)){
            this.data = data;
            System.out.println("new data:" + this.data);
            updateView(data);
        }
    }

    @Override
    public void updateView(Object data) {
        for( final View v: views){
            //if( v.isActive()){
                v.showData(data);
            //}
            
        }
    }
    
}
