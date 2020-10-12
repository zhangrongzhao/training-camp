package org.geekbang.homework.composite;

import org.geekbang.homework.composite.controls.*;

public class CompositeControlDemo {
    public static void main(String[] args) {
       Control winForm = new WinForm("WINDOW窗口");

       Control picture = new Picture("LOGO图片");
       winForm.addControl(picture);

       Control loginBtn=new Button("登录");
       winForm.addControl(loginBtn);

       Control registerBtn=new Button("注册");
       winForm.addControl(registerBtn);

       Control frame=new Frame("FRAME1");
       winForm.addControl(frame);

       Control userNameLabel=new Label("用户名");
       frame.addControl(userNameLabel);

       Control textBox=new TextBox("文本框");
       frame.addControl(textBox);

       Control passwordLabel=new Label("密码");
       frame.addControl(passwordLabel);

       Control passwordBox=new PasswordBox("密码框");
       frame.addControl(passwordBox);

       Control checkbox=new CheckBox("复选框");
       frame.addControl(checkbox);

       Control rememberMe=new TextBox("记住用户名");
       frame.addControl(rememberMe);

       Control noPassword=new LinkLable("忘记密码");
       frame.addControl(noPassword);

       winForm.draw();
    }
}
