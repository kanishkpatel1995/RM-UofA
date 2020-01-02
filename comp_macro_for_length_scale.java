// STAR-CCM+ macro: cross_section.java
// Written by STAR-CCM+ 13.06.012
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.vis.*;
import star.post.*;
import star.base.report.*;

public class comp_macro_for_length_scale extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() 
  {

    Simulation simulation_0 = 
      getActiveSimulation();


    SolutionHistory solutionHistory_0 = 
      ((SolutionHistory) simulation_0.get(SolutionHistoryManager.class).getObject("Parameters"));

    RecordedSolutionView recordedSolutionView_4 = 
      solutionHistory_0.createRecordedSolutionView(true);

          
  double k, trsfm ; 

  k = 0.01; /*First cross section is at z = +0.14, the last cross section will be at -0.126, the increments should be 0.001, that is 1mm*/
  trsfm = 0.0 ; 
  
  /* Creating plane section */

    PlaneSection planeSection_9 = 
      (PlaneSection) simulation_0.getPartManager().createImplicitPart(new NeoObjectVector(new Object[] {}), new DoubleVector(new double[] {0.0, 0.0, 1.0}), new DoubleVector(new double[] {0.0, 0.0, 0.0}), 0, 1, new DoubleVector(new double[] {0.0}));

    Coordinate coordinate_7 = 
      planeSection_9.getOriginCoordinate();

    Units units_0 = 
      ((Units) simulation_0.getUnitsManager().getObject("m"));

    coordinate_7.setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {0.0, 0.0, k}));

    planeSection_9.getInputParts().setQuery(null);

    Region region_0 = 
      simulation_0.getRegionManager().getRegion("Region");

    planeSection_9.getInputParts().setObjects(region_0);

    /* Creating reports and setting representation*/


    AreaAverageReport areaAverageReport_5 = 
      simulation_0.getReportManager().createReport(AreaAverageReport.class);

    PrimitiveFieldFunction primitiveFieldFunction_0 = 
      ((PrimitiveFieldFunction) simulation_0.getFieldFunctionManager().getFunction("Secondary Fluid Scalar"));

    areaAverageReport_5.setFieldFunction(primitiveFieldFunction_0);

    areaAverageReport_5.getParts().setQuery(null);

    areaAverageReport_5.getParts().setObjects(planeSection_9);

    UserFieldFunction userFieldFunction_3 = 
      simulation_0.getFieldFunctionManager().createFieldFunction();

    userFieldFunction_3.getTypeOption().setSelected(FieldFunctionTypeOption.Type.SCALAR);

    Units units_1 = 
      simulation_0.getUnitsManager().getPreferredUnits(new IntVector(new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));

    userFieldFunction_3.setDefinition("((${SurfaceAverage1Report} > 1e-3) && (${Secondary Fluid Scalar} > ${SurfaceAverage1Report})) ? 1.0:0.0");

    /* Creating scalar scene and setting representation */

    simulation_0.getSceneManager().createScalarScene("Scalar Scene", "Outline", "Scalar");

    Scene scene_4 = 
      simulation_0.getSceneManager().getScene("Scalar Scene 1");

    scene_4.initializeAndWait();

    PartDisplayer partDisplayer_6 = 
      ((PartDisplayer) scene_4.getDisplayerManager().getDisplayer("Outline 1"));

    partDisplayer_6.initialize();

    ScalarDisplayer scalarDisplayer_3 = 
      ((ScalarDisplayer) scene_4.getDisplayerManager().getDisplayer("Scalar 1"));

    scalarDisplayer_3.initialize();

    Legend legend_3 = 
      scalarDisplayer_3.getLegend();

    BlueRedLookupTable blueRedLookupTable_0 = 
      ((BlueRedLookupTable) simulation_0.get(LookupTableManager.class).getObject("blue-red"));

    legend_3.setLookupTable(blueRedLookupTable_0);

    SceneUpdate sceneUpdate_4 = 
      scene_4.getSceneUpdate();

    HardcopyProperties hardcopyProperties_4 = 
      sceneUpdate_4.getHardcopyProperties();

    hardcopyProperties_4.setCurrentResolutionWidth(1366);

    hardcopyProperties_4.setCurrentResolutionHeight(570);

    scene_4.resetCamera();

    scene_4.resetCamera();

    scene_4.setViewOrientation(new DoubleVector(new double[] {0.0, 0.0, -1.0}), new DoubleVector(new double[] {0.0, 1.0, 0.0}));

    partDisplayer_6.setVisibilityOverrideMode(DisplayerVisibilityOverride.HIDE_ALL_PARTS);

    scalarDisplayer_3.getInputParts().setQuery(null);

    scalarDisplayer_3.getInputParts().setObjects(planeSection_9);

    scalarDisplayer_3.getScalarDisplayQuantity().setFieldFunction(userFieldFunction_3);

    legend_3.setLevels(2);

    scalarDisplayer_3.setFillMode(ScalarFillMode.NODE_FILLED);

    scene_4.setAxesVisible(false);

      /*    SolutionRepresentation solutionRepresentation_3 = 
      ((SolutionRepresentation) simulation_0.getRepresentationManager().getObject("Passive Scalar Properties")); */

    legend_3.setVisible(false);

    /*Transform scenes such to get proper images*/

        SimpleTransform simpleTransform_2 = 
      simulation_0.getTransformManager().createSimpleTransform("Simple Transform");

    Coordinate coordinate_15 = 
      simpleTransform_2.getTranslationCoordinate();

    coordinate_15.setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {0.0, 0.0, -0.6}));

    /*Set simple transform for scalar scene created*/
    scalarDisplayer_3.setVisTransform(simpleTransform_2);

    /*
    
    for (int i = 140; i <= 310; ++i) 
    {
      coordinate_7.setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {0.0, 0.0, k}));
      
      for (int time = 1; time <= 400; ++time) 
     {
      /* Setting representation for the same cross section */
        /* Setting solution representation */

        /*
        SolutionRepresentation solutionRepresentation_3 = 
          ((SolutionRepresentation) simulation_0.getRepresentationManager().getObject("Parameters"));
        recordedSolutionView_4.setStateName("State " + Integer.toString(time));
        /* Setting representation for reports */

/*
        areaAverageReport_5.setRepresentation(solutionRepresentation_3);

        /*Setting representaion for Scalar Scene*/

       /*
        partDisplayer_6.setRepresentation(solutionRepresentation_3);
        scalarDisplayer_3.setRepresentation(solutionRepresentation_3);

        /* Saving the scalar scene*/
        /*
        scene_4.printAndWait(resolvePath("/home/kanishk/RESEARCH- MASTERS_LAB/Static_mixer Simulations/Kenics_mixer/KM_1_element_half_inch/Final Simulations/Final_TM_sim_Mesh3/re_12000_TM1_k_ep_realizable_two_layer_all_y_plus/Image_Data_For_Exposure/CrossSection"+Integer.toString(i)+"/scene"+ Integer.toString(time) + ".png"), 1, 1920, 1080, true, false);
      }
      
      k = k - 0.001;
      /* Change coordinates for transformation*/

      /*
      trsfm += 0.001;
      coordinate_15.setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {0.0, 0.0, (-0.6+(trsfm))}));
    
    }      */

/* differernt */
    for (int time = 0; time <= 400; ++time) 
    {
    	k = 0.01; /*First cross section is at z = +0.14, the last cross section will be at -0.126, the increments should be 0.001, that is 1mm*/
        trsfm = 0.0; /*Factor for Transforming to get all plane section at the same point in scene image*/
        coordinate_15.setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {0.0, 0.0, -0.6}));

    	/* Setting representation for the same cross section */
        /* Setting solution representation */
        SolutionRepresentation solutionRepresentation_3 = 
          ((SolutionRepresentation) simulation_0.getRepresentationManager().getObject("Parameters"));
        recordedSolutionView_4.setStateName("State " + Integer.toString(time));
        /* Setting representation for reports */

        areaAverageReport_5.setRepresentation(solutionRepresentation_3);

        /*Setting representaion for Scalar Scene*/
        partDisplayer_6.setRepresentation(solutionRepresentation_3);
        scalarDisplayer_3.setRepresentation(solutionRepresentation_3);
      
      for (int i = 140; i <= 310; ++i) 
     {



      coordinate_7.setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {0.0, 0.0, k}));

        /* Saving the scalar scene*/
        scene_4.printAndWait(resolvePath("/home/kanishk/RESEARCH- MASTERS_LAB/Static_mixer Simulations/Kenics_mixer/KM_1_element_half_inch/Final Simulations/Final_TM_sim_Mesh3/re_12000_TM1_k_ep_realizable_two_layer_all_y_plus/Image_Data_For_Exposure/CrossSection"+Integer.toString(i)+"/scene"+ Integer.toString(time) + ".png"), 1, 1920, 1080, true, false);

      k = k - 0.001;
      /* Change coordinates for transformation*/
      trsfm += 0.001;
      coordinate_15.setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {0.0, 0.0, (-0.6+(trsfm))}));

      }
    }




    /* Remove unnessary planes and scalars from simulation */

    simulation_0.getSceneManager().deleteScenes(new NeoObjectVector(new Object[] {scene_4}));

    simulation_0.getFieldFunctionManager().removeObjects(userFieldFunction_3);

    simulation_0.getReportManager().removeObjects(areaAverageReport_5);

    simulation_0.getTransformManager().removeObjects(simpleTransform_2);

    simulation_0.get(SolutionViewManager.class).removeSolutionViews(new NeoObjectVector(new Object[] {recordedSolutionView_4}));
  }
}
