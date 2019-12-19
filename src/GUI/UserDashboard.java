package GUI;

import Database.getData;
import Model.Event;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDashboard {
    private String name;
    private String Lastname;

    public UserDashboard(User user) throws SQLException {
        name= user.getName();
        Lastname = user.getLastname();
        JFrame frame = new JFrame("USER DASHBOARD");
        frame.setLayout(null);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize((int) dimension.getWidth(), (int) dimension.getHeight());

        ImagePanel panel = new ImagePanel(new ImageIcon("Background3.jpg").getImage());
        panel.setLayout(null);
        frame.getContentPane().add(panel);



        JLabel userName = new JLabel("Name: " +name);
        userName.setFont(new Font("Calibri", Font.PLAIN, 22));
        userName.setBounds(950, 10, 100, 100);
        panel.add(userName);

        JLabel userSurname = new JLabel("Surname: " + Lastname);
        userSurname.setFont(new Font("Calibri", Font.PLAIN, 22));
        userSurname.setBounds(1150, 10, 200, 100);
        panel.add(userSurname);

        JButton logout = new JButton("Logout");
        logout.setBounds(1300, 40, 100, 50);
        panel.add(logout);

        JLabel basket = new JLabel("Basket");
        basket.setBounds(300, 40, 100, 50);
        basket.setFont(new Font("Calibri", Font.BOLD,22));

        panel.add(basket);

        JLabel events = new JLabel("BILET");
        events.setFont(new Font("Calibri", Font.BOLD, 32));
        events.setBounds(700, 40, 500, 50);
        panel.add(events);

        JTextField search = new JTextField("Search here!") ;
        search.setFont(new Font("Calibri", Font.PLAIN, 22));
        search.setBounds(500,110,500,50);
        search.setVisible(true);
        panel.add(search);

        int y = 150;
        JButton event = null;
        ArrayList<Event> Allevents = getData.getAllEvents();

        for (int eventNumber = 0; eventNumber < Allevents.size(); eventNumber++) {
            Event SelectedEvent = Allevents.get(eventNumber);
            event = new JButton(SelectedEvent.getName());
            event.setFont(new Font("Calibri", Font.PLAIN, 22));
            event.setBounds(500, y + 10, 500, 100);
            panel.add(event);
            y += 100;

            event.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    DetailedEvent detailedEvent = new DetailedEvent(SelectedEvent);
                }
            });
/*
            event.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DetailedEvent detailedEvent = new DetailedEvent();
                }
            });*/
        }

        search.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //?
            }
        });
/*
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               //?
            }
        });
*/


        frame.setVisible(true);
    }

}
