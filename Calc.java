package com.YELUO.Test;
        import java.awt.BorderLayout;
        import java.awt.Color;
        import java.awt.GridLayout;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;

        import javax.swing.JButton;
        import javax.swing.JFrame;
        import javax.swing.JPanel;
        import javax.swing.JTextField;

//  好麻烦啊，就不写括号了把
public class Calc extends JFrame implements ActionListener {

    private String[] MainKey = { "7", "8", "9", "4", "5", "6", "1", "2", "3",
            "0", ".", " " };
    private String[] Cmd1 = { "+", "-", "*", "/" };
    private String[] Cmd2 = { "←", "C", " ", "=" };
    private JButton[] MK = new JButton[MainKey.length];
    private JButton[] C1 = new JButton[Cmd1.length];
    private JButton[] C2 = new JButton[Cmd2.length];
    private JTextField ans = new JTextField("");
    boolean judge = false;

    public Calc() {
        setTitle("傻子计算器");
        SetLayout();
        setResizable(false);
        setVisible(true);
        pack();
    }

    void SetLayout() {
        JPanel J_ans = new JPanel();
        J_ans.setLayout(new GridLayout());
        ans.setHorizontalAlignment(ans.RIGHT);
        ans.setEditable(false);
        ans.setBackground(Color.white);
        J_ans.add(ans);

        JPanel J_MK = new JPanel();
        J_MK.setLayout(new GridLayout(4, 3, 2, 2));
        for (int i = 0; i < MainKey.length; i++) {
            MK[i] = new JButton(MainKey[i]);
            MK[i].addActionListener(this);
            J_MK.add(MK[i]);
        }

        JPanel J_C1 = new JPanel();
        J_C1.setLayout(new GridLayout(4, 1, 2, 2));
        for (int i = 0; i < Cmd1.length; i++) {
            C1[i] = new JButton(Cmd1[i]);
            C1[i].addActionListener(this);
            J_C1.add(C1[i]);
        }

        JPanel J_C2 = new JPanel();
        J_C2.setLayout(new GridLayout(4, 1, 2, 2));
        for (int i = 0; i < Cmd2.length; i++) {
            C2[i] = new JButton(Cmd2[i]);
            C2[i].addActionListener(this);
            J_C2.add(C2[i]);
        }
        getContentPane().setLayout(new BorderLayout(2, 2));
        getContentPane().add("West", J_MK);
        getContentPane().add("Center", J_C1);
        getContentPane().add("East", J_C2);
        getContentPane().add("North", J_ans);
        setLocation(600, 300);
    }

    public String Get_ans(String now) {
        int len = now.length();
        int pos = 0, point, k = 0, Dtot = 0;
        String tmp;
        double[] Dnum = new double[30];
        String op = "";
        for (int i = 0; i <= len; i++) {
            tmp = "";
            point = 0;
            if (i == len || "+-*/".indexOf(now.charAt(i)) >= 0) {
                if (i != len) {
                    k++;
                    op += now.charAt(i);
                }
                for (int j = i - 1; j >= pos; j--) {
                    if (now.charAt(j) == '.')
                        point++;
                }
                tmp = now.substring(pos, i);
                if (point >= 2)
                    return tmp + " 搞不懂";
                Dnum[Dtot++] = Double.valueOf(tmp);
                pos = i + 1;
            }
        }
        if (k == 0)
            return "不算";
        for (int i = 0; i < k; i++) {
            if (op.charAt(i) == '*') {
                Dnum[i + 1] *= Dnum[i];
                Dnum[i] = 0;
            } else if (op.charAt(i) == '/') {
                if (Dnum[i + 1] == 0)
                    return "除数不能为0";
                Dnum[i + 1] = Dnum[i] / Dnum[i + 1];
                Dnum[i] = 0;
            }
        }
        String nop = "";
        char Lop = '+';
        for(int i = 0;i < k;i++){
            if(op.charAt(i)=='+') Lop = '+';
            else if(op.charAt(i)=='-') Lop = '-';
            if(op.charAt(i)=='*'||op.charAt(i)=='/') nop += Lop;
            else nop += op.charAt(i);
        }
        double res = Dnum[0];
        for (int i = 0; i < k; i++) {
            if(nop.charAt(i)=='-') res -= Dnum[i+1];
            else res += Dnum[i+1];
        }
        return String.valueOf(res);
    }

    public void actionPerformed(ActionEvent ae) {
        String now = ans.getText();
        String s = ae.getActionCommand();
        int len = now.length();
        if(judge == true){
            judge = false;
            ans.setText("");
        }
        else if (s.equals("C")){
            ans.setText("");
            judge = false;
        }
        else if (s.equals("←")) {
            if (len > 0) {
                now = now.substring(0, len - 1);
                ans.setText(now);
            }
        } else if (s.equals(" ") || len >= 30) {

        } else if ("+-*/".indexOf(s) >= 0) {
            if (len != 0 && "+-*/.".indexOf(now.charAt(len - 1)) < 0) {
                ans.setText(now + s);
            }
        } else if (s.equals("=")) {
            if (len != 0 && "+-*/.".indexOf(now.charAt(len - 1)) < 0) {
                ans.setText(Get_ans(now));
                judge = true;
            }
        } else if (s.equals(".")) {
            if (len != 0 && "1234567890".indexOf(now.charAt(len - 1)) >= 0)
                ans.setText(now + s);
        } else
            ans.setText(now + s);
    }

    public static void main(String args[]) {
        Calc c = new Calc();
    }
}