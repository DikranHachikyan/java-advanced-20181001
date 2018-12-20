/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package course;

/**
 *
 * @author wizard
 */
public class AppController implements Controller{
    private Model model = null;
    
    public AppController(Model model){
        this.model = model;
    }
    @Override
    public boolean validateInput(Object data) {
        if( data == null || ! (data instanceof String) ) return false;
        String value = data.toString();
        System.out.println("Validate Input " + data);
        try{
            
            float  floatValue = Float.parseFloat(value);
            model.setData( (floatValue * 1000) );
        }
        catch(NumberFormatException | NullPointerException e){
            e.printStackTrace();
            return false;
        }
        return true;   
    }
    
}
