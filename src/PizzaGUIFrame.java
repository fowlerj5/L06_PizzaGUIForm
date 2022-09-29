import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PizzaGUIFrame extends JFrame
{
    JPanel mainPnl, titlePnl, displayPnl, cmdPnl, orderPnl, crustPnl, sizePnl, toppingPnl, optionsPnl;
    JLabel titleLbl, panelLbl;
    JScrollPane scroller;
    JTextArea orderTA;
    JButton quitBtn, orderBtn, clearBtn;
    JRadioButton thinBtn, regBtn, deepBtn;
    ButtonGroup crustBtnGrp;
    JComboBox sizeBox;
    JCheckBox cheese, pepperoni, pineapple, anchovies, beans, tomato, basil, chicken, croutons, ranch;
    String[] sizes= {"Small ($8)","Medium ($12)","Large ($16)","Super ($20)"};

    public PizzaGUIFrame() throws HeadlessException
    {
        setTitle("Pizza Order");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int scrnHeight = screenSize.height;
        int scrnWidth = screenSize.width;
        setSize(scrnWidth*3/4, scrnHeight*3/4);
        setLocation(scrnWidth/8, scrnHeight/8);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());
        add(mainPnl);

        createTitlePanel();
        createCommandPanel();
        createOrderPanel();

        setVisible(true);
    }
    private void createCommandPanel()
    {
        cmdPnl = new JPanel();
        cmdPnl.setLayout(new GridLayout(1,3));

        quitBtn = new JButton("Quit");
        quitBtn.setFont(new Font("Bold", Font.BOLD, 18));
        orderBtn = new JButton("Order");
        orderBtn.setFont(new Font("Bold", Font.BOLD, 18));
        clearBtn = new JButton("Clear");
        clearBtn.setFont(new Font("Bold", Font.BOLD, 18));

        quitBtn.addActionListener((ActionEvent ae) ->
        {
            String quit = "Are you sure you want to quit?";
            if (JOptionPane.showConfirmDialog(null, quit,"Quit", JOptionPane.YES_NO_OPTION)==0) {
                System.exit(0);
            }
        });
        orderBtn.addActionListener((ActionEvent ae) ->
        {
            double subTotalDbl, crustPriceDbl, toppingPriceDbl;
            String type, typef, crustPrice, toppingPrice, subTotal, subTotalPrice, tax, taxPrice, total, totalPrice;
            orderTA.setText("");
            if ((thinBtn.isSelected() || regBtn.isSelected() || deepBtn.isSelected())
                    && sizeBox.getSelectedIndex()!=-1 &&
                    (cheese.isSelected() || pepperoni.isSelected() || pineapple.isSelected() || anchovies.isSelected() || beans.isSelected() || tomato.isSelected() || basil.isSelected() || chicken.isSelected() || croutons.isSelected() || ranch.isSelected())) {
                subTotalDbl = 0.00;
                type = crustBtnGrp.getSelection().getActionCommand() + ", " + sizes[sizeBox.getSelectedIndex()];
                typef = " " + String.format("%-32s", type);
                crustPriceDbl = 8.00 + 4*sizeBox.getSelectedIndex();
                subTotalDbl += crustPriceDbl;
                crustPrice =  "$" + String.format("%5.2f", crustPriceDbl);
                toppingPriceDbl = 1.00;
                toppingPrice = "$" + String.format("%5.2f", toppingPriceDbl);

                orderTA.append("\n" + typef + crustPrice + "\n\n");

                if (cheese.isSelected()) {type = "Extra Cheese"; typef = " " + String.format("%-32s", type); subTotalDbl += toppingPriceDbl; orderTA.append(typef); orderTA.append(toppingPrice + "\n");}
                if (pepperoni.isSelected()) {type = "Pepperoni"; typef = " " + String.format("%-32s", type); subTotalDbl += toppingPriceDbl; orderTA.append(typef); orderTA.append(toppingPrice + "\n");}
                if (pineapple.isSelected()) {type = "Pineapple"; typef = " " + String.format("%-32s", type); subTotalDbl += toppingPriceDbl; orderTA.append(typef); orderTA.append(toppingPrice + "\n");}
                if (anchovies.isSelected()) {type = "Anchovies"; typef = " " + String.format("%-32s", type); subTotalDbl += toppingPriceDbl; orderTA.append(typef); orderTA.append(toppingPrice + "\n");}
                if (beans.isSelected()) {type = "Baked Beans"; typef = " " + String.format("%-32s", type); subTotalDbl += toppingPriceDbl; orderTA.append(typef); orderTA.append(toppingPrice + "\n");}
                if (tomato.isSelected()) {type = "Sliced Tomato"; typef = " " + String.format("%-32s", type); subTotalDbl += toppingPriceDbl; orderTA.append(typef); orderTA.append(toppingPrice + "\n");}
                if (basil.isSelected()) {type = "Basil"; typef = " " + String.format("%-32s", type); subTotalDbl += toppingPriceDbl; orderTA.append(typef); orderTA.append(toppingPrice + "\n");}
                if (chicken.isSelected()) {type = "BBQ Chicken"; typef = " " + String.format("%-32s", type); subTotalDbl += toppingPriceDbl; orderTA.append(typef); orderTA.append(toppingPrice + "\n");}
                if (croutons.isSelected()) {type = "Croutons"; typef = " " + String.format("%-32s", type); subTotalDbl += toppingPriceDbl; orderTA.append(typef); orderTA.append(toppingPrice + "\n");}
                if (ranch.isSelected()) {type = "Ranch Dressing"; typef = " " + String.format("%-32s", type); subTotalDbl += toppingPriceDbl; orderTA.append(typef); orderTA.append(toppingPrice + "\n");}
                orderTA.append(" ");
                for (int i = 1; i < 39; i++) {
                    orderTA.append("-");
                }
                orderTA.append("\n");

                subTotal = " " + String.format("%-32s", "Sub-Total");
                subTotalPrice = "$" + String.format("%5.2f", subTotalDbl);
                tax = " " + String.format("%-32s", "Tax (7%)");
                taxPrice = "$" + String.format("%5.2f", subTotalDbl*0.07);
                total = " " + String.format("%-32s", "Total");
                totalPrice = "$" + String.format("%5.2f", subTotalDbl*1.07);

                orderTA.append(subTotal + subTotalPrice + "\n");
                orderTA.append(tax + taxPrice + "\n ");
                for (int i = 1; i < 39; i++) {
                    orderTA.append("=");
                }
                orderTA.append("\n" + total + totalPrice + "\n");
            } else if ((!thinBtn.isSelected() && !regBtn.isSelected() && !deepBtn.isSelected())
                    && sizeBox.getSelectedIndex()==-1 &&
                    (!cheese.isSelected() && !pepperoni.isSelected() && !pineapple.isSelected() && !anchovies.isSelected() && !beans.isSelected() && !tomato.isSelected() && !basil.isSelected() && !chicken.isSelected() && !croutons.isSelected() && !ranch.isSelected())) {
                orderTA.append(" Please select a crust type, size\n and at least one ingredient.");
            } else if (!thinBtn.isSelected() && !regBtn.isSelected() && !deepBtn.isSelected() && sizeBox.getSelectedIndex()==-1) {
                orderTA.append(" Please select a crust type and size.");
            } else if ((!thinBtn.isSelected() && !regBtn.isSelected() && !deepBtn.isSelected()) &&
                    (!cheese.isSelected() && !pepperoni.isSelected() && !pineapple.isSelected() && !anchovies.isSelected() && !beans.isSelected() && !tomato.isSelected() && !basil.isSelected() && !chicken.isSelected() && !croutons.isSelected() && !ranch.isSelected())) {
                orderTA.append(" Please select a crust type\n and at least one ingredient.");
            } else if (sizeBox.getSelectedIndex()==-1 &&
                    (!cheese.isSelected() && !pepperoni.isSelected() && !pineapple.isSelected() && !anchovies.isSelected() && !beans.isSelected() && !tomato.isSelected() && !basil.isSelected() && !chicken.isSelected() && !croutons.isSelected() && !ranch.isSelected())) {
                orderTA.append(" Please select a size\n and at least one ingredient.");
            } else if (!thinBtn.isSelected() && !regBtn.isSelected() && !deepBtn.isSelected()) {
                orderTA.append(" Please select a crust type.");
            } else if (sizeBox.getSelectedIndex()==-1) {
                orderTA.append(" Please select a size.");
            } else if ((!cheese.isSelected() && !pepperoni.isSelected() && !pineapple.isSelected() && !anchovies.isSelected() && !beans.isSelected() && !tomato.isSelected() && !basil.isSelected() && !chicken.isSelected() && !croutons.isSelected() && !ranch.isSelected())) {
                orderTA.append(" Please select at least one ingredient.");
            }
        });
        clearBtn.addActionListener((ActionEvent ae) ->
        {
            crustBtnGrp.clearSelection();
            sizeBox.setSelectedIndex(-1);
            cheese.setSelected(false);
            pepperoni.setSelected(false);
            pineapple.setSelected(false);
            anchovies.setSelected(false);
            beans.setSelected(false);
            tomato.setSelected(false);
            basil.setSelected(false);
            chicken.setSelected(false);
            croutons.setSelected(false);
            ranch.setSelected(false);
            orderTA.setText("");
        });

        cmdPnl.add(orderBtn);
        cmdPnl.add(clearBtn);
        cmdPnl.add(quitBtn);

        mainPnl.add(cmdPnl, BorderLayout.SOUTH);
    }
    private void createTitlePanel()
    {
        titlePnl = new JPanel();

        titleLbl = new JLabel("Pizza Order Form", JLabel.CENTER);
        titleLbl.setVerticalTextPosition(JLabel.TOP);
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);
        titleLbl.setFont(new Font("Bold Italic", Font.BOLD | Font.ITALIC, 36));

        titlePnl.add(titleLbl);

        mainPnl.add(titlePnl, BorderLayout.NORTH);
    }
    private void createOrderPanel()
    {
        orderPnl = new JPanel();

        createOptionsPanel();
        createDisplayPanel();

        mainPnl.add(orderPnl, BorderLayout.CENTER);
    }
    private void createOptionsPanel()
    {
        optionsPnl = new JPanel();
        optionsPnl.setLayout(new BoxLayout(optionsPnl, BoxLayout.Y_AXIS));

        createCrustPanel();
        optionsPnl.add(new JLabel("\n"));
        optionsPnl.add(new JLabel("\n"));
        createSizePanel();
        optionsPnl.add(new JLabel("\n"));
        optionsPnl.add(new JLabel("\n"));
        createToppingPanel();

        orderPnl.add(optionsPnl);
    }
    private void createDisplayPanel()
    {
        displayPnl = new JPanel();

        orderTA = new JTextArea(21,40);
        orderTA.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        orderTA.setEditable(false);
        scroller = new JScrollPane(orderTA);

        displayPnl.add(scroller);

        orderPnl.add(displayPnl);
    }
    private void createCrustPanel()
    {
        crustPnl = new JPanel();
        crustPnl.setLayout(new GridLayout(0,2));
        panelLbl = new JLabel("Crust:");

        thinBtn = new JRadioButton("Thin");
        thinBtn.setActionCommand("Thin");
        regBtn = new JRadioButton("Regular");
        regBtn.setActionCommand("Regular");
        deepBtn = new JRadioButton("Deep Dish");
        deepBtn.setActionCommand("Deep Dish");

        crustBtnGrp = new ButtonGroup();
        crustBtnGrp.add(thinBtn);
        crustBtnGrp.add(regBtn);
        crustBtnGrp.add(deepBtn);

        crustPnl.add(panelLbl);
        crustPnl.add(thinBtn);
        crustPnl.add(new JLabel(""));
        crustPnl.add(regBtn);
        crustPnl.add(new JLabel(""));
        crustPnl.add(deepBtn);

        optionsPnl.add(crustPnl, BorderLayout.WEST);
    }
    private void createSizePanel()
    {
        sizePnl = new JPanel();
        sizePnl.setLayout(new GridLayout(0,2));
        panelLbl = new JLabel("Size:");


        sizeBox = new JComboBox(sizes);
        sizeBox.setSelectedIndex(-1);

        sizePnl.add(panelLbl);
        sizePnl.add(sizeBox);

        optionsPnl.add(sizePnl, BorderLayout.CENTER);
    }
    private void createToppingPanel()
    {
        toppingPnl = new JPanel();
        toppingPnl.setLayout(new GridLayout(0,2));
        panelLbl = new JLabel("Toppings (+$1 each):     ");

        cheese = new JCheckBox("Extra Cheese");
        pepperoni = new JCheckBox("Pepperoni");
        pineapple = new JCheckBox("Pineapple");
        anchovies = new JCheckBox("Anchovies");
        beans = new JCheckBox("Baked Beans");
        tomato = new JCheckBox("Sliced Tomato");
        basil = new JCheckBox("Basil");
        chicken = new JCheckBox("BBQ Chicken");
        croutons = new JCheckBox("Croutons");
        ranch = new JCheckBox("Ranch Dressing");

        toppingPnl.add(panelLbl);
        toppingPnl.add(cheese);
        toppingPnl.add(new JLabel(""));
        toppingPnl.add(pepperoni);
        toppingPnl.add(new JLabel(""));
        toppingPnl.add(pineapple);
        toppingPnl.add(new JLabel(""));
        toppingPnl.add(anchovies);
        toppingPnl.add(new JLabel(""));
        toppingPnl.add(beans);
        toppingPnl.add(new JLabel(""));
        toppingPnl.add(tomato);
        toppingPnl.add(new JLabel(""));
        toppingPnl.add(basil);
        toppingPnl.add(new JLabel(""));
        toppingPnl.add(chicken);
        toppingPnl.add(new JLabel(""));
        toppingPnl.add(croutons);
        toppingPnl.add(new JLabel(""));
        toppingPnl.add(ranch);

        optionsPnl.add(toppingPnl, BorderLayout.EAST);
    }
}
