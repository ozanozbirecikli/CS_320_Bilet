package GUI;

import Database.DeleteData;
import Database.UpdateData;
import Database.getData;
import Model.Event;
import Model.Organizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrganizerDashboard {
    private int id;
    private String name;
    private String lastname;
    private String orgemail;
    private ArrayList<Event> myevents;

    private JButton createEvent;
    private JButton myEvents;
    private JLabel events;
    private JFrame myEventsFrame;
    private Boolean isEmpty;

    public OrganizerDashboard(Organizer organizer) throws SQLException{

        id = organizer.getId();
        name = organizer.getName();
        lastname = organizer.getLastname();
        orgemail = organizer.getEmail();

        isEmpty = true;

        organizerui();
    }

    public void organizerui() {


        final JFrame frame = new JFrame("ORGANIZER DASHBOARD");

        frame.setLayout(null);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize((int) dimension.getWidth(), (int) dimension.getHeight());


        ImagePanel panel = new ImagePanel(new ImageIcon("Background3.jpg").getImage());
        panel.setLayout(null);
        frame.getContentPane().add(panel);

        JLabel organizerName = new JLabel("Name: " + name);
        organizerName.setFont(new Font("Calibri", Font.PLAIN, 22));
        organizerName.setBounds(100, 100, 500, 100);
        organizerName.setForeground(Color.WHITE);
        panel.add(organizerName);

        JLabel organizerSurname = new JLabel("Surname: " + lastname);
        organizerSurname.setFont(new Font("Calibri", Font.PLAIN, 22));
        organizerSurname.setBounds(100, 200, 500, 100);
        organizerSurname.setForeground(Color.WHITE);
        panel.add(organizerSurname);

        JLabel email = new JLabel("Email: " + orgemail);
        email.setFont(new Font("Calibri", Font.PLAIN, 22));
        email.setBounds(100, 300, 500, 100);
        email.setForeground(Color.WHITE);
        panel.add(email);

        createEvent = new JButton("Create Event");
        createEvent.setBounds(100, 450, 130, 50);
        panel.add(createEvent);

        myEvents = new JButton("My Events");
        myEvents.setBounds(340, 450, 130, 50);
        panel.add(myEvents);

        JButton logout = new JButton("Logout");
        logout.setBounds(220, 550, 130, 50);
        panel.add(logout);


        events = new JLabel("All Events");
        events.setFont(new Font("Calibri", Font.BOLD, 32));
        events.setBounds(700, 60, 130, 50);
        events.setBackground(Color.WHITE);
        panel.add(events);



        createEvent.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e) {
                EventCreation newEvent = new EventCreation(id);

            }
        });

        myEvents.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e) {

                myevents();
            }
        });

        logout.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Logged out Succesfully!");
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

    public JButton getCreateEvent() {
        return createEvent;
    }

    public JButton getMyEventsButton() {
        return myEvents;
    }

    public JLabel getAllEvents() {
        return events;
    }

    public JFrame getMyEventsFrame() {
        return myEventsFrame;
    }


    public ArrayList<Event> getMyEventsList() {
        return myevents;
    }

    public Boolean getEmpty() {
        return isEmpty;
    }

    private void myevents() {

        try {
            myevents = getData.getEvents(false, orgemail);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (myevents.size() < 1) {
            isEmpty = true;
            JOptionPane.showMessageDialog(null, "Sorry! You haven't created any event yet");
        } else {

            isEmpty = false;
            events_gui();
        }


    }

    private void events_gui() {

        myEventsFrame = new JFrame("My Events");

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        myEventsFrame.setSize(1000, 600);
        int x = (int) ((dimension.getWidth() - myEventsFrame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - myEventsFrame.getHeight()) / 2);
        myEventsFrame.setLocation(x, y);


        JPanel listpanel = new JPanel();
        listpanel.setLayout(null);
        myEventsFrame.add(listpanel);

        final JList list = new JList();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setBounds(150, 50, 500, 400);
        list.setFont(new Font("Calibri", Font.PLAIN, 20));
        listpanel.add(list);

        final DefaultListModel dlm = new DefaultListModel();
        for (int i = 0; i < myevents.size(); i++) {
            dlm.addElement(myevents.get(i).getName());

        }
        list.setModel(dlm);
        JButton btnupdate = new JButton("Change");
        btnupdate.setBounds(700, 200, 100, 50);
        listpanel.add(btnupdate);


        JButton deleteEvent = new JButton("Delete Event");
        deleteEvent.setBounds(700, 325, 150, 50);
        listpanel.add(deleteEvent);

        deleteEvent.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                int index = list.getSelectedIndex();
                int eventid = myevents.get(index).getId();
                DeleteData.deleteEvent(eventid);

                dlm.removeElementAt(index);
                JOptionPane.showMessageDialog(null, "Event Deleted Successfully!");
            }
        });

        btnupdate.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                final JFrame frame = new JFrame("New Event");

                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                frame.setSize(600, 450);
                int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
                int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
                frame.setLocation(x, y);

                frame.setVisible(true);
                JPanel panel = new JPanel();
                panel.setBackground(Color.gray);
                panel.setLayout(null);

                frame.add(panel);

                JLabel eventName = new JLabel("Event Name:");
                eventName.setFont(new Font("Calibri", Font.PLAIN, 15));
                eventName.setBounds(50, 50, 120, 30);
                panel.add(eventName);

                final JTextField eventText = new JTextField();
                eventText.setFont(new Font("Calibri", Font.PLAIN, 15));
                eventText.setBounds(180, 50, 300, 30);
                panel.add(eventText);

                JLabel event_ticket = new JLabel("Ticket Number:");
                event_ticket.setFont(new Font("Calibri", Font.PLAIN, 15));
                event_ticket.setBounds(50, 100, 120, 30);
                panel.add(event_ticket);

                final JTextField ticket_num = new JTextField();
                ticket_num.setFont(new Font("Calibri", Font.PLAIN, 15));
                ticket_num.setBounds(180, 100, 300, 30);
                panel.add(ticket_num);

                JLabel event_date = new JLabel("Date:");
                event_date.setFont(new Font("Calibri", Font.PLAIN, 15));
                event_date.setBounds(50, 150, 120, 30);
                panel.add(event_date);

               final JTextField dateText = new JTextField();
                dateText.setFont(new Font("Calibri", Font.PLAIN, 15));
                dateText.setBounds(180, 150, 300, 30);
                panel.add(dateText);

                JLabel eventLocation = new JLabel("Location:");
                eventLocation.setFont(new Font("Calibri", Font.PLAIN, 15));
                eventLocation.setBounds(50, 200, 120, 30);
                panel.add(eventLocation);

                final JTextField locationText = new JTextField();
                locationText.setFont(new Font("Calibri", Font.PLAIN, 15));
                locationText.setBounds(180, 200, 300, 30);
                panel.add(locationText);

                JLabel eventPrice = new JLabel("Price:");
                eventPrice.setFont(new Font("Calibri", Font.PLAIN, 15));
                eventPrice.setBounds(50, 250, 120, 30);
                panel.add(eventPrice);

                final JTextField priceText = new JTextField();
                priceText.setFont(new Font("Calibri", Font.PLAIN, 15));
                priceText.setBounds(180, 250, 300, 30);
                panel.add(priceText);

                JLabel eventiban = new JLabel("IBAN:");
                eventiban.setFont(new Font("Calibri", Font.PLAIN, 15));
                eventiban.setBounds(50, 300, 120, 30);
                panel.add(eventiban);

                final JTextField ibanno = new JTextField();
                ibanno.setFont(new Font("Calibri", Font.PLAIN, 15));
                ibanno.setBounds(180, 300, 300, 30);
                panel.add(ibanno);

                JButton button = new JButton("Update Event");
                button.setBounds(x / 2, 350, 150, 30);
                panel.add(button);

                button.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {

                        Event evt = myevents.get(list.getSelectedIndex());
                        int id = evt.getId();
                        int orgid = evt.getOrganizerId();
                        String name = eventText.getText();
                        int ticket = Integer.parseInt(ticket_num.getText());
                        String date = dateText.getText();
                        String location = locationText.getText();
                        int price = Integer.parseInt(priceText.getText());
                        String iban = ibanno.getText();


                        UpdateData.updateEvent(id, name, orgid, ticket, date, location, price, iban);

                        JOptionPane.showMessageDialog(null, "Event updated succesfully!");
                        frame.dispose();


                    }
                });

            }
        });
        myEventsFrame.setVisible(true);

    }
}
