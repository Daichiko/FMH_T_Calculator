package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BackEnd.Calculator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Calculator calculator;
	private JPanel panelInputsPriceRoads;
	private JLabel lblPricepermile;
	private JTextField textPricepermile;
	private JLabel lblRoadAB;
	private JTextField textRoadAB;
	private JLabel lblRoadBC;
	private JTextField textRoadBC;
	private JLabel lblRoadCA;
	private JTextField textRoadCA;
	private JTextField textPriceBroucker;
	private JLabel lblPricePerMileInformation;
	private JRadioButton rdbtnBoxtruck;
	private JRadioButton rdbtnSemitruck;
	private JLabel lblPriceBrouckerInformation;
	private JLabel lblTypeTruck;
	private JLabel lblRoadsTittle;
	private JPanel panelOuputs;
	private JTextField txtMinPriceMiles;
	private JLabel lblMinPriceMile;
	private JLabel lblTotalDistTrip;
	private JTextField textTotalDistTrip;
	private JLabel lblRealLoadPrice;
	private JTextField textRealLoadPrice;
	private JLabel lblLoadPriceBoucker;
	private JTextField textLoadPriceBroucker;
	private JLabel lblAcceptableLoad;
	private JTextField textLoadIs;
	private JRadioButton rdbtnPerMiles;
	private JRadioButton rdbtnTotalRoute;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		calculator = new Calculator();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1043, 455);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textPricepermile.getText().isEmpty() || textRoadAB.getText().isEmpty() || textRoadBC.getText().isEmpty() || textRoadCA.getText().isEmpty() || textPriceBroucker.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "One or more fields are empty", "warning", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				
				double milesRoadAB = Double.parseDouble(textRoadAB.getText().toString());
				double milesRoadBC = Double.parseDouble(textRoadBC.getText().toString());
				double milesRoadCA = Double.parseDouble(textRoadCA.getText().toString());
				
				double totalMilesDouble = calculator.calculateTotalMiles(milesRoadAB, milesRoadBC, milesRoadCA);
				
				String totalMilesString = Double.toString(totalMilesDouble);
				
				textTotalDistTrip.setText(totalMilesString);
				
				if (calculator.isLoadAceptable(rdbtnBoxtruck.isSelected(), totalMilesDouble)) {
					textLoadIs.setText("TAKEABLE");
					textLoadIs.setForeground(Color.GREEN.darker());
				}else {
					textLoadIs.setText("UNTAKEABLE");
					textLoadIs.setForeground(Color.RED);
				};
				
				
				double Pricepermiledouble = Double.parseDouble(textPricepermile.getText().toString());
				double MinPriceMilesDouble = calculator.calculateMinPrice(Pricepermiledouble); 
				
				String MinPriceMilesString = Double.toString(MinPriceMilesDouble);
				
				txtMinPriceMiles.setText(MinPriceMilesString);
				
				
				double LoadPriceBrouckerDouble;
				String LoadPriceBrouckerString;
				
				if (rdbtnPerMiles.isSelected()) {
					double PriceBrouckerDouble = Double.parseDouble(textPriceBroucker.getText().toString());
					LoadPriceBrouckerDouble = calculator.calculateCargoValue(PriceBrouckerDouble, milesRoadBC);
					LoadPriceBrouckerString = Double.toString(LoadPriceBrouckerDouble);
				}else {
					LoadPriceBrouckerDouble = Double.parseDouble(textPriceBroucker.getText().toString());
					LoadPriceBrouckerString = textPriceBroucker.getText().toString();	
				}
				
				textLoadPriceBroucker.setText(LoadPriceBrouckerString);			
				
				double RealLoadPriceDouble = calculator.calculateRealPayment(LoadPriceBrouckerDouble, totalMilesDouble);
				String RealLoadPriceString = Double.toString(RealLoadPriceDouble);;
			
				textRealLoadPrice.setText(RealLoadPriceString);
				
				if (LoadPriceBrouckerDouble > MinPriceMilesDouble) {
					textLoadPriceBroucker.setForeground(Color.GREEN.darker());
					textRealLoadPrice.setForeground(Color.GREEN.darker());
				}
				else {
					textLoadPriceBroucker.setForeground(Color.RED);
					textRealLoadPrice.setForeground(Color.RED);
				}	
			}
		});
		btnCalculate.setBounds(106, 363, 85, 21);
		contentPane.add(btnCalculate);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnExit.setBounds(878, 363, 85, 21);
		contentPane.add(btnExit);
		
		JLabel lblTitle = new JLabel("FMH Transportation LLC Calculator");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblTitle.setBounds(331, 24, 381, 40);
		contentPane.add(lblTitle);
		
		panelInputsPriceRoads = new JPanel();
		panelInputsPriceRoads.setBorder(UIManager.getBorder("CheckBox.border"));
		panelInputsPriceRoads.setBounds(48, 93, 322, 246);
		contentPane.add(panelInputsPriceRoads);
		panelInputsPriceRoads.setLayout(null);
		
		lblPricepermile = new JLabel("Minimum price per mile:");
		lblPricepermile.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblPricepermile.setBounds(23, 43, 113, 13);
		panelInputsPriceRoads.add(lblPricepermile);
		
		textPricepermile = new JTextField();
		textPricepermile.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();	        
				
				if (Character.isDigit(keyChar))
			        return;
			    
			    if (keyChar == '.' && !textPricepermile.getText().contains(".")) {
			        return;
			    }
			    
			    e.consume();
			}
		});
		textPricepermile.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textPricepermile.setBounds(146, 40, 96, 19);
		textPricepermile.setColumns(10);
		panelInputsPriceRoads.add(textPricepermile);
		
		lblRoadAB = new JLabel("Road Trip AB:");
		lblRoadAB.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblRoadAB.setBounds(23, 148, 73, 13);
		panelInputsPriceRoads.add(lblRoadAB);
		
		textRoadAB = new JTextField();
		textRoadAB.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();	        
				
				if (Character.isDigit(keyChar))
			        return;
			    
			    if (keyChar == '.' && !textRoadAB.getText().contains(".")) {
			        return;
			    }
			    
			    e.consume();
			}
		});
		textRoadAB.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textRoadAB.setBounds(106, 145, 96, 19);
		textRoadAB.setColumns(10);
		panelInputsPriceRoads.add(textRoadAB);
		
		lblRoadBC = new JLabel("Road Trip BC:");
		lblRoadBC.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblRoadBC.setBounds(23, 176, 73, 13);
		panelInputsPriceRoads.add(lblRoadBC);
		
		textRoadBC = new JTextField();
		textRoadBC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();	        
				
				if (Character.isDigit(keyChar))
			        return;
			    
			    if (keyChar == '.' && !textRoadBC.getText().contains(".")) {
			        return;
			    }
			    
			    e.consume();
			}
		});
		textRoadBC.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textRoadBC.setBounds(106, 173, 96, 19);
		textRoadBC.setColumns(10);
		panelInputsPriceRoads.add(textRoadBC);
		
		lblRoadCA = new JLabel("Road Trip CA:");
		lblRoadCA.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblRoadCA.setBounds(23, 204, 73, 13);
		panelInputsPriceRoads.add(lblRoadCA);
		
		textRoadCA = new JTextField();
		textRoadCA.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();	        
				
				if (Character.isDigit(keyChar))
			        return;
			    
			    if (keyChar == '.' && !textRoadCA.getText().contains(".")) {
			        return;
			    }
			    
			    e.consume();
			}
		});
		textRoadCA.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textRoadCA.setBounds(106, 202, 96, 19);
		textRoadCA.setColumns(10);
		panelInputsPriceRoads.add(textRoadCA);
		
		String labelText = "<html>" +
                "Road Trip AB: From the Yard to the charge zone<br>" +
                "Road Trip BC: From the charge zone to the discharge zone<br>" +
                "Road Trip CA: From the discharge zone to the Yard" +
                "</html>";
		JLabel lblRoadsInformation = new JLabel(labelText);
		lblRoadsInformation.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblRoadsInformation.setBounds(23, 81, 289, 57);
		panelInputsPriceRoads.add(lblRoadsInformation);
		
		lblPricePerMileInformation = new JLabel("Minimum acceptable price per mile");
		lblPricePerMileInformation.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblPricePerMileInformation.setBounds(23, 20, 289, 13);
		panelInputsPriceRoads.add(lblPricePerMileInformation);
		
		lblRoadsTittle = new JLabel("Roads distance:");
		lblRoadsTittle.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblRoadsTittle.setBounds(23, 76, 113, 13);
		panelInputsPriceRoads.add(lblRoadsTittle);
		
		JPanel panelInputsBrouckerTrucks = new JPanel();
		panelInputsBrouckerTrucks.setBorder(UIManager.getBorder("CheckBox.border"));
		panelInputsBrouckerTrucks.setBounds(380, 93, 239, 246);
		contentPane.add(panelInputsBrouckerTrucks);
		panelInputsBrouckerTrucks.setLayout(null);
		
		JLabel lblBrouckerPrice = new JLabel("Broucker Price:");
		lblBrouckerPrice.setBounds(28, 43, 70, 13);
		lblBrouckerPrice.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panelInputsBrouckerTrucks.add(lblBrouckerPrice);
		
		textPriceBroucker = new JTextField();
		textPriceBroucker.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();	        
				
				if (Character.isDigit(keyChar))
			        return;
			    
			    if (keyChar == '.' && !textPriceBroucker.getText().contains(".")) {
			        return;
			    }
			    
			    e.consume();
			}
		});
		textPriceBroucker.setBounds(108, 40, 96, 19);
		textPriceBroucker.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textPriceBroucker.setColumns(10);
		panelInputsBrouckerTrucks.add(textPriceBroucker);
		
		rdbtnPerMiles = new JRadioButton("Price per mile");
		rdbtnPerMiles.setBounds(28, 62, 89, 21);
		rdbtnPerMiles.setFont(new Font("Tahoma", Font.PLAIN, 10));
		rdbtnPerMiles.setSelected(true);
		panelInputsBrouckerTrucks.add(rdbtnPerMiles);
		
		rdbtnTotalRoute = new JRadioButton("Price per complete road");
		rdbtnTotalRoute.setBounds(28, 85, 139, 21);
		rdbtnTotalRoute.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panelInputsBrouckerTrucks.add(rdbtnTotalRoute);
		
		ButtonGroup buttonGroupTypePay = new ButtonGroup();
		buttonGroupTypePay.add(rdbtnPerMiles);
		buttonGroupTypePay.add(rdbtnTotalRoute);
        
        rdbtnBoxtruck = new JRadioButton("Boxtruck");
        rdbtnBoxtruck.setSelected(true);
        rdbtnBoxtruck.setFont(new Font("Tahoma", Font.PLAIN, 10));
        rdbtnBoxtruck.setBounds(28, 143, 89, 21);
        panelInputsBrouckerTrucks.add(rdbtnBoxtruck);
        
        rdbtnSemitruck = new JRadioButton("Semitruck");
        rdbtnSemitruck.setFont(new Font("Tahoma", Font.PLAIN, 10));
        rdbtnSemitruck.setBounds(28, 166, 139, 21);
        panelInputsBrouckerTrucks.add(rdbtnSemitruck);
        
        ButtonGroup buttonGroupTypeTruck = new ButtonGroup();
        buttonGroupTypeTruck.add(rdbtnBoxtruck);
        buttonGroupTypeTruck.add(rdbtnSemitruck);
        
        lblPriceBrouckerInformation = new JLabel("Broucker payment method");
        lblPriceBrouckerInformation.setFont(new Font("Tahoma", Font.BOLD, 10));
        lblPriceBrouckerInformation.setBounds(27, 20, 187, 13);
        panelInputsBrouckerTrucks.add(lblPriceBrouckerInformation);
        
        lblTypeTruck = new JLabel("Type of truck:");
        lblTypeTruck.setFont(new Font("Tahoma", Font.BOLD, 10));
        lblTypeTruck.setBounds(28, 124, 187, 13);
        panelInputsBrouckerTrucks.add(lblTypeTruck);
        
        panelOuputs = new JPanel();
        panelOuputs.setBorder(UIManager.getBorder("CheckBox.border"));
        panelOuputs.setBounds(629, 93, 356, 246);
        contentPane.add(panelOuputs);
        panelOuputs.setLayout(null);
        
        txtMinPriceMiles = new JTextField(); 
        txtMinPriceMiles.setFont(new Font("Tahoma", Font.PLAIN, 10));
        txtMinPriceMiles.setBounds(139, 60, 194, 19);
        txtMinPriceMiles.setEditable(false);
        panelOuputs.add(txtMinPriceMiles);
        txtMinPriceMiles.setColumns(10);
        
        lblMinPriceMile = new JLabel("Minimum acceptable price:");
        lblMinPriceMile.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblMinPriceMile.setBounds(10, 63, 135, 13);
        panelOuputs.add(lblMinPriceMile);
        
        lblTotalDistTrip = new JLabel("Total Distance Trip:");
        lblTotalDistTrip.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblTotalDistTrip.setBounds(10, 17, 105, 13);
        panelOuputs.add(lblTotalDistTrip);
        
        textTotalDistTrip = new JTextField();
        textTotalDistTrip.setFont(new Font("Tahoma", Font.PLAIN, 10));
        textTotalDistTrip.setBounds(139, 14, 194, 19);
        textTotalDistTrip.setEditable(false);
        panelOuputs.add(textTotalDistTrip);
        textTotalDistTrip.setColumns(10);
        
        lblRealLoadPrice = new JLabel("Price per complete Load:");
        lblRealLoadPrice.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblRealLoadPrice.setBounds(10, 109, 135, 13);
        panelOuputs.add(lblRealLoadPrice);
        
        textRealLoadPrice = new JTextField();
        textRealLoadPrice.setFont(new Font("Tahoma", Font.PLAIN, 10));
        textRealLoadPrice.setBounds(139, 106, 194, 19);
        textRealLoadPrice.setEditable(false);
        panelOuputs.add(textRealLoadPrice);
        textRealLoadPrice.setColumns(10);
        
        lblLoadPriceBoucker = new JLabel("Load Price Broucker:");
        lblLoadPriceBoucker.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblLoadPriceBoucker.setBounds(10, 86, 105, 13);
        panelOuputs.add(lblLoadPriceBoucker);
        
        textLoadPriceBroucker = new JTextField();
        textLoadPriceBroucker.setFont(new Font("Tahoma", Font.PLAIN, 10));
        textLoadPriceBroucker.setBounds(139, 83, 194, 19);
        textLoadPriceBroucker.setEditable(false);
        panelOuputs.add(textLoadPriceBroucker);
        textLoadPriceBroucker.setColumns(10);
        
        lblAcceptableLoad = new JLabel("The Load is");
        lblAcceptableLoad.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblAcceptableLoad.setBounds(10, 40, 68, 13);
        panelOuputs.add(lblAcceptableLoad);
        
        textLoadIs = new JTextField();
        textLoadIs.setFont(new Font("Tahoma", Font.PLAIN, 10));
        textLoadIs.setBounds(139, 37, 194, 19);
        textLoadIs.setEditable(false);
        panelOuputs.add(textLoadIs);
        textLoadIs.setColumns(10);
	}
}
