// STAR-CCM+ macro: Passive_scalar_iso_surfaces.java
// Written by STAR-CCM+ 14.04.013
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.base.report.*;
import star.vis.*;
import star.post.*;

public class Passive_scalar_iso_surfaces extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    Scene scene_0 = 
      simulation_0.getSceneManager().getScene("Geometry Scene 2");

    scene_0.setViewOrientation(new DoubleVector(new double[] {-1.0, 0.0, 0.0}), new DoubleVector(new double[] {0.0, -1.0, 0.0}));

    CurrentView currentView_0 = 
      scene_0.getCurrentView();

    currentView_0.setInput(new DoubleVector(new double[] {0.007830824130070912, 0.01944098475682792, -0.0343992412258427}), new DoubleVector(new double[] {-0.30392213981076194, 0.01944098475682792, -0.0343992412258427}), new DoubleVector(new double[] {0.0, -1.0, 0.0}), 0.16349681150472445, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {0.007013950285354342, 0.023656889506647908, -0.034672035062595766}), new DoubleVector(new double[] {-0.36907928917102195, 0.023656889506647908, -0.034672035062595766}), new DoubleVector(new double[] {0.0, -1.0, 0.0}), 0.16349681150472445, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {0.007138341483086952, 0.02165220066521405, -0.015197914888665336}), new DoubleVector(new double[] {-0.36907928917102195, 0.02165220066521405, -0.015197914888665336}), new DoubleVector(new double[] {0.0, -1.0, 0.0}), 0.16349681150472445, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {0.007311429712566486, 0.002455636818786854, -0.012332756105607436}), new DoubleVector(new double[] {-0.36907928917102195, 0.002455636818786854, -0.012332756105607436}), new DoubleVector(new double[] {0.0, -1.0, 0.0}), 0.16349681150472445, 0, 30.0);


    SolutionHistory solutionHistory_0 = 
      ((SolutionHistory) simulation_0.get(SolutionHistoryManager.class).getObject("Parameters"));

      RecordedSolutionView recordedSolutionView_1 = 
      solutionHistory_0.createRecordedSolutionView(true);

   /*RecordedSolutionView recordedSolutionView_1 = 
      ((RecordedSolutionView) simulation_0.get(SolutionViewManager.class).getObject("Parameters")); */

    PartDisplayer partDisplayer_0 = 
      ((PartDisplayer) scene_0.getDisplayerManager().getDisplayer("Outline 1"));

    SolutionRepresentation solutionRepresentation_1 = 
      ((SolutionRepresentation) simulation_0.getRepresentationManager().getObject("Parameters"));

    partDisplayer_0.setRepresentation(solutionRepresentation_1);

    PartDisplayer partDisplayer_1 = 
      ((PartDisplayer) scene_0.getDisplayerManager().getDisplayer("Geometry 1"));

    partDisplayer_1.setRepresentation(solutionRepresentation_1);

    ScalarDisplayer scalarDisplayer_0 = 
      ((ScalarDisplayer) scene_0.getDisplayerManager().getDisplayer("Isosurface Scalar 1"));

    scalarDisplayer_0.setRepresentation(solutionRepresentation_1);

    ScalarDisplayer scalarDisplayer_1 = 
      ((ScalarDisplayer) scene_0.getDisplayerManager().getDisplayer("Isosurface Scalar 2"));

    scalarDisplayer_1.setRepresentation(solutionRepresentation_1);

    Legend legend_1 = 
      scalarDisplayer_1.getLegend();

    legend_1.setVisible(false);

    VolumeAverageReport volumeAverageReport_0 = 
      ((VolumeAverageReport) simulation_0.getReportManager().getReport("Volume Average of Secondary Fluid Scalar"));

    volumeAverageReport_0.setRepresentation(solutionRepresentation_1);

    currentView_0.setInput(new DoubleVector(new double[] {0.007311429712566486, 0.002455636818786854, -0.012332756105607436}), new DoubleVector(new double[] {-0.36907928917102195, 0.002455636818786854, -0.012332756105607436}), new DoubleVector(new double[] {0.0, -1.0, 0.0}), 0.16349681150472445, 0, 30.0);

    for (int time = 100; time <= 400; ++time) 

    {

    recordedSolutionView_1.setStateName("State " + Integer.toString(time));

    scene_0.printAndWait(resolvePath("/home/kanishk/RESEARCH- MASTERS_LAB/Static_mixer Simulations/Kenics_mixer/KM_1_element_half_inch/Final Simulations/Final_TM_sim_Mesh3/re_12000_TM1_k_ep_realizable_two_layer_all_y_plus/Images_video_iso_surfaces/iso_surf_SPS_" + Integer.toString(time) +".png"), 1, 3840, 2160, true, false);

    }

    

    simulation_0.get(SolutionViewManager.class).removeSolutionViews(new NeoObjectVector(new Object[] {recordedSolutionView_1}));
  }
}
