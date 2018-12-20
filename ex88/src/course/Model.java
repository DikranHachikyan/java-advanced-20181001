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
public interface Model {
    public void registerView(View view);
    public void setData(Object data);
    public void updateView(Object data);
}
