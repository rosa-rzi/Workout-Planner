package ui;

import model.Exercise;
import model.Schedule;
import model.TrainingDay;
import model.Workout;
import persistance.JsonReader;
import persistance.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Represents the graphical user interface (GUI) for the workoutPlanner application. It allows
 * users to view their workout schedule for the week, add workouts to the schedule and add
 * exercises to a workout, save schedule and load previous schedule.
 */
public class WorkoutPlannerGUI extends JFrame  {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 500;
    private static final String newline = "\n";
    private static final String JSON_STORE = "./data/schedule.json";
    public Schedule schedule;
    public TrainingDay trainingDay;
    public JsonWriter jsonWriter;
    public JsonReader jsonReader;
    private JPanel schedulePanel;
    private JPanel menuPanel;
    private JTextArea textArea;
    private JTextField enameTextField;
    private JButton buttonAddWorkout;
    private JButton buttonAddExercise;
    private JButton buttonSaveSchedule;
    private JButton buttonLoadSchedule;
    public String[] days = {"Sunday",
            "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    public String[] workoutsChoose = {"Choose workout"};
    private JLabel repLabel;
    private JLabel enameLabel;
    private JLabel setLabel;
    private JPanel exercisePanel;
    private JComboBox dayOption;
    private JLabel dayLabel;
    private JSpinner sets;
    private JSpinner reps;
    private JLabel wnameLabel;
    public JTextField wnameTextField;
    public JComboBox chooseWorkout;
    private JButton buttonViewSchedule;


    // EFFECTS: constructs window for the workout planner
    public WorkoutPlannerGUI() {
        super("Fitness Planner");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(WIDTH, HEIGHT));
        setVisible(true);
        createComponents();
        schedule = new Schedule();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }


    // MODIFIES: this
    // EFFECTS: creates graphical components: frame, panel and buttons
    private void createComponents() {
        createSchedulePanel();
        createMenuPanel();
        visualTitle();

    }

    // MODIFIES: this
    // EFFECTS: creates the constructed JLabels of the title and of the image, setting their sizes and fonts,
    // to the frame; (code gotten from stack exchange (I lost the link...cleared my history))
    public void visualTitle() {
        JLabel title = new JLabel("           WORKOUT PLANNER");
        JLabel image = new JLabel();

        title.setFont(new Font("SansSerif", Font.BOLD, 30));
        image.setIcon(new ImageIcon("./data/barbell2.jpeg"));
        image.setSize(new Dimension(5,1));
        add(title, BorderLayout.NORTH);
        menuPanel.add(image);

    }

    // MODIFIES: this
    // EFFECTS: creates left side of window were all new workouts will be printed to.
    private void createSchedulePanel() {
        schedulePanel = new JPanel();
        schedulePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        schedulePanel.setLayout(new GridLayout(0, 1));
        schedulePanel.setPreferredSize(new Dimension(WIDTH * 2 / 3, HEIGHT));
        Color c = new Color(130, 195, 230);
        schedulePanel.setBackground(c);
        add(schedulePanel, BorderLayout.WEST);

        textArea = new JTextArea();
        textArea.setFont(new Font("Helvetica", Font.PLAIN, 20));
        textArea.setEditable(false);
        textArea.setBackground(Color.WHITE);
        textArea.setColumns(1);

        schedulePanel.add(textArea);

    }




    private void createMenuPanel() {
        menuPanel = new JPanel();
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        menuPanel.setLayout(new GridLayout(0, 1));
        dayLabel = new JLabel("Choose workout day:\n");
        dayOption = new JComboBox(days);
        dayOption.setEditable(true);
        wnameLabel = new JLabel("\n Enter name of workout");
        wnameTextField = new JTextField(20);
        buttonAddWorkout = new JButton("Add Workout");
        buttonAddExercise = new JButton("Add Exercise");
        buttonViewSchedule = new JButton("View Schedule");
        buttonSaveSchedule = new JButton("Save Schedule");
        buttonLoadSchedule = new JButton("Load Schedule");
        menuPanel.add(buttonAddWorkout);
        menuPanel.add(buttonAddExercise);
        menuPanel.add(buttonSaveSchedule);
        menuPanel.add(buttonLoadSchedule);
        menuPanel.add(buttonViewSchedule);
        add(menuPanel, BorderLayout.EAST);
        setUpActions();
    }

    // EFFECTS: creates action events for buttons
    private void setUpActions() {
        chooseWorkout = new JComboBox(workoutsChoose);
        setButtonAddExercise();
        setButtonAddWorkout();
        saveAction();
        loadAction();
        setButtonViewSchedule();
    }

    // EFFECTS: sets up actionEventListener for buttonAddExercise
    public void setButtonAddExercise() {
        buttonAddExercise.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, makeExerciseDialogue());
                String ename = enameTextField.getText();
                String selected = (String) chooseWorkout.getSelectedItem();
                int numSets = (int) sets.getValue();
                int numReps = (int) reps.getValue();
                Workout w = schedule.findWorkout(selected);
                w.addExercise(new Exercise(ename, numSets, numReps));
                updateSchedule(w);
            }
        });
    }

    // EFFECTS: creates dialogue for when buttonAddExercise is clicked.
    public JPanel makeExerciseDialogue() {
        enameLabel = new JLabel("Exercise name:\n");
        setLabel = new JLabel("\n Enter number of sets:");
        repLabel = new JLabel("\n Enter number of reps:");
        enameTextField = new JTextField(20);
        sets = new JSpinner();
        reps = new JSpinner();
        exercisePanel = new JPanel();
        exercisePanel.add(chooseWorkout);
        exercisePanel.add(enameLabel);
        exercisePanel.add(enameTextField);
        exercisePanel.add(setLabel);
        exercisePanel.add(sets);
        exercisePanel.add(repLabel);
        exercisePanel.add(reps);
        return exercisePanel;
    }

    // EFFECTS: sets up actionListener for when ButtonAddWorkout in menuPanel is clicked.
    public void setButtonAddWorkout() {
        buttonAddWorkout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeWorkoutDialogue();
                TrainingDay day = setTrainingDay((String) dayOption.getSelectedItem());
                String name = wnameTextField.getText();
                Workout workout = new Workout(name, day);
                schedule.addWorkout(workout);
                updateSchedule(workout);
                chooseWorkout.addItem(name);
            }
        });

    }



    // EFFECTS: creates dialogue for when buttonAddWorkout is clicked.
    public void makeWorkoutDialogue() {
        dayLabel = new JLabel("Choose workout day:\n");
        dayOption = new JComboBox(days);
        dayOption.setEditable(true);
        wnameLabel = new JLabel("\n Enter name of workout");
        wnameTextField = new JTextField(20);

        JPanel workoutPanel = new JPanel();
        workoutPanel.add(wnameLabel);
        workoutPanel.add(wnameTextField);
        workoutPanel.add(dayLabel);
        workoutPanel.add(dayOption);
        JOptionPane.showMessageDialog(null, workoutPanel);

    }

    // EFFECTS: sets trainingDay based on what user selects for dayOption
    public TrainingDay setTrainingDay(String day) {
        if (day.equals("Sunday")) {
            return TrainingDay.SUNDAY;
        } else if (day.equals("Monday")) {
            return  TrainingDay.MONDAY;
        } else if (day.equals("Tuesday")) {
            return TrainingDay.TUESDAY;
        } else if (day.equals("Wednesday")) {
            return TrainingDay.WEDNESDAY;
        } else if (day.equals("Thursday")) {
            return TrainingDay.THURSDAY;
        } else if (day.equals("Friday")) {
            return TrainingDay.FRIDAY;
        } else if (day.equals("Saturday")) {
            return TrainingDay.SATURDAY;
        }
        return null;
    }




    // EFFECTS: prints out the workouts in the schedule
    public void updateSchedule(Workout workout) {
        textArea.setText(workout.workoutName + " on " + workout.getTrainingDay() + ":" + workout.getNamesOfExercises()
                + newline);

    }

    // MODIFIES: this
    // EFFECTS: saves the plan to file
    public void saveAction() {
        buttonSaveSchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent i) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(schedule);
                    jsonWriter.close();
                    textArea.setText("Saved " + " to " + JSON_STORE);
                } catch (FileNotFoundException e) {
                    textArea.setText("Unable to write to file: " + JSON_STORE);
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: loads plan from file
    public void loadAction() {
        buttonLoadSchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent i) {
                try {
                    schedule = jsonReader.read();
                    textArea.setText("Loaded " + " from " + JSON_STORE);
                } catch (IOException e) {
                    textArea.setText("Unable to read from file: " + JSON_STORE);
                }
            }
        });
    }


    // EFFECTS: sets up actionListener for when buttonViewSchedule is clicked
    public void setButtonViewSchedule() {
        buttonViewSchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                textArea.append("Sunday: " + schedule.getWorkoutName(TrainingDay.SUNDAY) + newline);
                textArea.append("Monday: " + schedule.getWorkoutName(TrainingDay.MONDAY) + newline);
                textArea.append("Tuesday: " + schedule.getWorkoutName(TrainingDay.TUESDAY) + newline);
                textArea.append("Wednesday: " + schedule.getWorkoutName(TrainingDay.WEDNESDAY) + newline);
                textArea.append("Thursday: " + schedule.getWorkoutName(TrainingDay.THURSDAY) + newline);
                textArea.append("Friday: " + schedule.getWorkoutName(TrainingDay.FRIDAY) + newline);
                textArea.append("Saturday: " + schedule.getWorkoutName(TrainingDay.SATURDAY) + newline);

            }
        });
    }

}
