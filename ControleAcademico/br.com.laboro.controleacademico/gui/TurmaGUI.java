package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import classes.backend.Turma;
import dao.TurmaDAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class TurmaGUI extends JInternalFrame {
	private JTextField tfNomeTurma;
	String turno;
	private JTextField tfAnoLetivo;

	public TurmaGUI() throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		setFrameIcon(new ImageIcon("/home/caio/icones/turmas.png"));
		getContentPane().setBackground(new Color(211, 211, 211));

		setBackground(Color.LIGHT_GRAY);
		setBorder(new LineBorder(new Color(255, 255, 0), 5));
		setClosable(true);
		setTitle("Turma");
		setIconifiable(true);
		setBounds(100, 100, 674, 512);
		getContentPane().setLayout(null);

		JLabel lblNomeDaTurma = new JLabel("Nome da Turma");
		lblNomeDaTurma.setBounds(72, 119, 112, 21);
		getContentPane().add(lblNomeDaTurma);

		// campos de texto
		tfNomeTurma = new JTextField();
		tfNomeTurma.setBounds(194, 119, 127, 25);
		getContentPane().add(tfNomeTurma);
		tfNomeTurma.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Cadastro de Turmas",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(128, 128,
						0)));
		panel.setBackground(new Color(211, 211, 211));
		panel.setBounds(32, 32, 600, 337);
		getContentPane().add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "turnos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(297, 132, 197, 147);
		panel.add(panel_1);
		panel_1.setLayout(null);

		final JRadioButton rdNoturno = new JRadioButton("Noturno");
		rdNoturno.setBounds(40, 93, 112, 18);
		panel_1.add(rdNoturno);
		rdNoturno.setBackground(new Color(233, 150, 122));
		rdNoturno.setForeground(UIManager.getColor("Button.foreground"));

		final JRadioButton rdVespertino = new JRadioButton("Vespertino");
		rdVespertino.setBounds(40, 60, 122, 21);
		panel_1.add(rdVespertino);
		rdVespertino.setBackground(new Color(233, 150, 122));
		rdVespertino.setForeground(UIManager.getColor("Button.foreground"));

		final JRadioButton rdMatutino = new JRadioButton("Matutino");
		rdMatutino.setBounds(40, 25, 112, 23);
		panel_1.add(rdMatutino);
		rdMatutino.setForeground(UIManager.getColor("Button.foreground"));
		rdMatutino.setBackground(new Color(233, 150, 122));
		
				tfAnoLetivo = new JTextField();
				tfAnoLetivo.setBounds(166, 132, 86, 25);
				panel.add(tfAnoLetivo);
				tfAnoLetivo.setColumns(10);
				// etiquetas
				JLabel lblAnoLetivo = new JLabel("Ano Letivo");
				lblAnoLetivo.setBounds(88, 135, 78, 14);
				panel.add(lblAnoLetivo);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(null);
		btnSalvar.setBackground(UIManager.getColor("Button.background"));
		btnSalvar.setForeground(new Color(128, 0, 0));
		btnSalvar.setToolTipText("Salvar Turma.");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Turma turma = new Turma();

				turma.setNomeTurma(tfNomeTurma.getText());// pegando o nome da
															// Turma

				

				int anoletivo = Integer.parseInt(tfAnoLetivo.getText());// idem
				turma.setAno(anoletivo);// idem idem

				if (tfNomeTurma.getText().isEmpty()) {
					JOptionPane
							.showMessageDialog(null, "Turma não cadastrada!");
				}

				else {
					if (rdMatutino.isSelected()) {
						turno = "Matutino";
						turma.setTurno(turno);
					} else if (rdVespertino.isSelected()) {
						turno = "Vespertino";
						turma.setTurno(turno);
					} else if (rdNoturno.isSelected()) {
						turno = "Noturno";
						turma.setTurno(turno);
					}

					JOptionPane.showMessageDialog(null,
							"Turma " + tfNomeTurma.getText()
									+ " cadastrada com sucesso!");
					TurmaDAO dao;
					try {
						dao = new TurmaDAO();
						dao.adicionarTurma(turma);
						dispose();
					} catch (Exception e) {

						e.printStackTrace();
					}

				}
			}
		});
		btnSalvar.setBounds(543, 401, 89, 23);
		getContentPane().add(btnSalvar);

	}
}
