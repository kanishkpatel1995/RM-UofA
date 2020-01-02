// STAR-CCM+ macro: post_processing_data_saving.java
// Written by STAR-CCM+ 14.04.013
package macro;



import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.vis.*;

public class TM1_all_two_layer_post_process_turb_parameter_scenes extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {
    double k, l ;
    k = 0.14;
    l = 0.0;

    Simulation simulation_0 = 
      getActiveSimulation();

/* Creating Plane for calculating Radial velocity at interval of 1mm and then saving it */


    PlaneSection planeSection_0 = 
      (PlaneSection) simulation_0.getPartManager().createImplicitPart(new NeoObjectVector(new Object[] {}), new DoubleVector(new double[] {0.0, 0.0, 1.0}), new DoubleVector(new double[] {0.0, 0.0, 0.0}), 0, 1, new DoubleVector(new double[] {0.0}));

    planeSection_0.getInputParts().setQuery(null);

    PlaneSection planeSection_1 = 
      ((PlaneSection) simulation_0.getPartManager().getObject("x_normal"));

    planeSection_0.getInputParts().setObjects(planeSection_1);

    Units units_0 = 
      ((Units) simulation_0.getUnitsManager().getObject("m"));

    planeSection_0.getOriginCoordinate().setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {0.0, 0.0, 0.14}));

    PrimitiveFieldFunction primitiveFieldFunction_0 = 
      ((PrimitiveFieldFunction) simulation_0.getFieldFunctionManager().getFunction("Velocity"));

    VectorComponentFieldFunction vectorComponentFieldFunction_0 = 
      ((VectorComponentFieldFunction) primitiveFieldFunction_0.getComponentFunction(2));

    VectorMagnitudeFieldFunction vectorMagnitudeFieldFunction_0 = 
      ((VectorMagnitudeFieldFunction) primitiveFieldFunction_0.getMagnitudeFunction());

    /*fieldFunctionUnits_0.setFieldFunction(vectorComponentFieldFunction_0);*/



   /* Cartesian2DAxisManager cartesian2DAxisManager_2 = 
      ((Cartesian2DAxisManager) xYPlot_1.getAxisManager());

    cartesian2DAxisManager_2.setAxesBounds(new Vector(Arrays.asList(new AxisManager.AxisBounds("Bottom Axis", -0.006341363357900448, false, 0.006341363352484742, false))));

    cartesian2DAxisManager_2.setAxesBounds(new Vector(Arrays.asList(new AxisManager.AxisBounds("Left Axis", -1.0119621753692627, false, -0.038497962057590485, false))));*/

    



    
    /*Velocity, TKE, TVR and Tdr Maps at each mm */


    PlaneSection planeSection_2 = 
      (PlaneSection) simulation_0.getPartManager().createImplicitPart(new NeoObjectVector(new Object[] {}), new DoubleVector(new double[] {0.0, 0.0, 1.0}), new DoubleVector(new double[] {0.0, 0.0, 0.0}), 0, 1, new DoubleVector(new double[] {0.0}));


    planeSection_2.getOriginCoordinate().setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {0.0, 0.0, 0.14}));

    Region region_0 = 
      simulation_0.getRegionManager().getRegion("Region");

    planeSection_2.getInputParts().setObjects(region_0);

    simulation_0.getSceneManager().createScalarScene("Scalar Scene", "Outline", "Scalar");

    Scene scene_1 = 
      simulation_0.getSceneManager().getScene("Scalar Scene 1");

    scene_1.initializeAndWait();

    PartDisplayer partDisplayer_3 = 
      ((PartDisplayer) scene_1.getDisplayerManager().getDisplayer("Outline 1"));

    SimpleTransform simpleTransform_0 = 
      simulation_0.getTransformManager().createSimpleTransform("Simple Transform");



    partDisplayer_3.initialize();

    ScalarDisplayer scalarDisplayer_0 = 
      ((ScalarDisplayer) scene_1.getDisplayerManager().getDisplayer("Scalar 1"));



    scalarDisplayer_0.initialize();

    Legend legend_0 = 
      scalarDisplayer_0.getLegend();

    BlueRedLookupTable blueRedLookupTable_0 = 
      ((BlueRedLookupTable) simulation_0.get(LookupTableManager.class).getObject("blue-red"));

    legend_0.setLookupTable(blueRedLookupTable_0);


      scene_1.resetCamera();

      partDisplayer_3.setVisibilityOverrideMode(DisplayerVisibilityOverride.HIDE_ALL_PARTS);


      scalarDisplayer_0.getInputParts().setQuery(null);

    scalarDisplayer_0.getInputParts().setObjects(planeSection_2);

    PrimitiveFieldFunction primitiveFieldFunction_1 = 
      ((PrimitiveFieldFunction) simulation_0.getFieldFunctionManager().getFunction("TurbulentKineticEnergy"));

    scalarDisplayer_0.getScalarDisplayQuantity().setFieldFunction(primitiveFieldFunction_1);

    PrimitiveFieldFunction primitiveFieldFunction_2 = 
      ((PrimitiveFieldFunction) simulation_0.getFieldFunctionManager().getFunction("TurbulentViscosityRatio"));

    PrimitiveFieldFunction primitiveFieldFunction_10 = 
      ((PrimitiveFieldFunction) simulation_0.getFieldFunctionManager().getFunction("TurbulentDissipationRate"));


    scalarDisplayer_0.getScalarDisplayQuantity().setFieldFunction(primitiveFieldFunction_2);

    scene_1.resetCamera();

    scene_1.setViewOrientation(new DoubleVector(new double[] {0.0, 0.0, -1.0}), new DoubleVector(new double[] {0.0, 1.0, 0.0}));

    scene_1.resetCamera();

    simpleTransform_0.getTranslationCoordinate().setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {0.0, 0.0, 0.0}));

    scalarDisplayer_0.setVisTransform(simpleTransform_0);

    partDisplayer_3.setVisTransform(simpleTransform_0);

    scalarDisplayer_0.setFillMode(ScalarFillMode.NODE_FILLED);

    legend_0.setWidth(0.03);

    legend_0.setLevels(100);

    legend_0.updateLayout(new DoubleVector(new double[] {0.72, 0.175}), 0.03, 0.6, 1);

    /* Setting colour bar */

    legend_0.setNumberOfLabels(10);

    legend_0.setLabelFormat("%3.2f");

    scene_1.setAxesVisible(false);

    legend_0.setFontString("LMRoman10-Bold");

    legend_0.setLabelHeight(0.03);



    for (int i = 1; i <= 63; ++i) 
      { 
        planeSection_2.getOriginCoordinate().setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {0.0, 0.0, l})); 

        simpleTransform_0.getTranslationCoordinate().setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {0.0, 0.0, -0.48-l}));
        
        scalarDisplayer_0.getScalarDisplayQuantity().setFieldFunction(primitiveFieldFunction_1);

        scalarDisplayer_0.getScalarDisplayQuantity().setAutoRange(AutoRangeMode.NONE);

        scalarDisplayer_0.getScalarDisplayQuantity().setRange(new DoubleVector(new double[] {0.0, 0.42}));
        
        scene_1.printAndWait(resolvePath("/home/kanishk/RESEARCH- MASTERS_LAB/Static_mixer Simulations/Kenics_mixer/KM_1_element_half_inch/Final Simulations/Final_TM_sim_Mesh3/Data Post Processing/TKE/TM 1/TM1_re_12000_KM_1_ele_k_ep_TKE_"+ Integer.toString(i)+"mm.png"), 1, 1280, 720, true, true);
         
        scalarDisplayer_0.getScalarDisplayQuantity().setFieldFunction(primitiveFieldFunction_2);

        scalarDisplayer_0.getScalarDisplayQuantity().setAutoRange(AutoRangeMode.NONE);

        scalarDisplayer_0.getScalarDisplayQuantity().setRange(new DoubleVector(new double[] {0.0, 134}));
        
        scene_1.printAndWait(resolvePath("/home/kanishk/RESEARCH- MASTERS_LAB/Static_mixer Simulations/Kenics_mixer/KM_1_element_half_inch/Final Simulations/Final_TM_sim_Mesh3/Data Post Processing/TVR/TM 1/TM1_re_12000_KM_1_ele_k_ep_TVR_" + Integer.toString(i)+"mm.png"), 1, 1280, 720, true, true);
        
        scalarDisplayer_0.getScalarDisplayQuantity().setFieldFunction(vectorMagnitudeFieldFunction_0);

        scalarDisplayer_0.getScalarDisplayQuantity().setAutoRange(AutoRangeMode.NONE);

        scalarDisplayer_0.getScalarDisplayQuantity().setRange(new DoubleVector(new double[] {0.0, 2.4}));
        
        scene_1.printAndWait(resolvePath("/home/kanishk/RESEARCH- MASTERS_LAB/Static_mixer Simulations/Kenics_mixer/KM_1_element_half_inch/Final Simulations/Final_TM_sim_Mesh3/Data Post Processing/Velocity Magnitude/TM 1/TM1_re_12000_KM_1_ele_k_ep_vel_mag_" + Integer.toString(i)+"mm.png"), 1, 1280, 720, true, true);

        scalarDisplayer_0.getScalarDisplayQuantity().setFieldFunction(primitiveFieldFunction_10);

        scalarDisplayer_0.getScalarDisplayQuantity().setAutoRange(AutoRangeMode.NONE);

        scalarDisplayer_0.getScalarDisplayQuantity().setRange(new DoubleVector(new double[] {0.0, 300}));

        scene_1.printAndWait(resolvePath("/home/kanishk/RESEARCH- MASTERS_LAB/Static_mixer Simulations/Kenics_mixer/KM_1_element_half_inch/Final Simulations/Final_TM_sim_Mesh3/Data Post Processing/Tdr/TM 1/TM1_re_12000_KM_1_ele_k_ep_tdr_" + Integer.toString(i)+"mm.png"), 1, 1280, 720, true, true);


        l = l - 0.002;     
      }
    

 /* Getting y-normal Scalar Plots */

    partDisplayer_3.getInputParts().setQuery(null);

    PlaneSection planeSection_3 = 
      ((PlaneSection) simulation_0.getPartManager().getObject("y_normal"));

    partDisplayer_3.getInputParts().setObjects(planeSection_3);

    scalarDisplayer_0.getInputParts().setQuery(null);

    scalarDisplayer_0.getInputParts().setObjects(planeSection_3);

    scene_1.resetCamera();

    IdentityTransform identityTransform_0 = 
      ((IdentityTransform) simulation_0.getTransformManager().getObject("Identity"));

    partDisplayer_3.setVisTransform(identityTransform_0);

    scalarDisplayer_0.setVisTransform(identityTransform_0);

    scene_1.resetCamera();

    scene_1.setViewOrientation(new DoubleVector(new double[] {0.0, -1.0, 0.0}), new DoubleVector(new double[] {1.0, 0.0, 0.0}));

    scene_1.resetCamera();

    legend_0.updateLayout(new DoubleVector(new double[] {0.22000000000000003, 0.13}), 0.6, 0.03, 0);

    scene_1.setAxesVisible(true);
    
    scene_1.setAxesViewport(new DoubleVector(new double[] {0.0, 0.0, 0.3, 0.4}));

    SimpleTransform simpleTransform_1 = 
      simulation_0.getTransformManager().createSimpleTransform("Simple Transform");

    partDisplayer_3.setVisTransform(simpleTransform_1);

    scalarDisplayer_0.setVisTransform(simpleTransform_1);

    simpleTransform_1.getTranslationCoordinate().setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {0.0, 0.0, 0.0}));

    scene_1.resetCamera();

    simpleTransform_1.getTranslationCoordinate().setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {0.0, -0.2, 0.0}));

    scalarDisplayer_0.getScalarDisplayQuantity().setFieldFunction(vectorMagnitudeFieldFunction_0);

    scene_1.printAndWait(resolvePath("/home/kanishk/RESEARCH- MASTERS_LAB/Static_mixer Simulations/Kenics_mixer/KM_1_element_half_inch/Final Simulations/Final_TM_sim_Mesh3/Data Post Processing/Y normal/TM1_re_12000_KM_1_ele_k_ep_Vel_magnitude.png"), 1, 1280, 720, true, true);

    PrimitiveFieldFunction primitiveFieldFunction_3 = 
      ((PrimitiveFieldFunction) simulation_0.getFieldFunctionManager().getFunction("Pressure"));

    scalarDisplayer_0.getScalarDisplayQuantity().setFieldFunction(primitiveFieldFunction_3);

    scene_1.printAndWait(resolvePath("/home/kanishk/RESEARCH- MASTERS_LAB/Static_mixer Simulations/Kenics_mixer/KM_1_element_half_inch/Final Simulations/Final_TM_sim_Mesh3/Data Post Processing/Y normal/TM1_re_12000_KM_1_ele_k_ep_pressure.png"), 1, 1280, 720, true, true);

    scalarDisplayer_0.getScalarDisplayQuantity().setFieldFunction(primitiveFieldFunction_1);

    scalarDisplayer_0.getScalarDisplayQuantity().setAutoRange(AutoRangeMode.NONE);

    scalarDisplayer_0.getScalarDisplayQuantity().setRange(new DoubleVector(new double[] {0.0, 0.28}));

    scene_1.printAndWait(resolvePath("/home/kanishk/RESEARCH- MASTERS_LAB/Static_mixer Simulations/Kenics_mixer/KM_1_element_half_inch/Final Simulations/Final_TM_sim_Mesh3/Data Post Processing/Y normal/TM1_re_12000_KM_1_ele_k_ep_TKE.png"), 1, 1280, 720, true, true);

    scalarDisplayer_0.getScalarDisplayQuantity().setFieldFunction(primitiveFieldFunction_2);

    scene_1.printAndWait(resolvePath("/home/kanishk/RESEARCH- MASTERS_LAB/Static_mixer Simulations/Kenics_mixer/KM_1_element_half_inch/Final Simulations/Final_TM_sim_Mesh3/Data Post Processing/Y normal/TM1_re_12000_KM_1_ele_k_ep_TVR.png"), 1, 1280, 720, true, true);

    scalarDisplayer_0.getScalarDisplayQuantity().setFieldFunction(primitiveFieldFunction_10);

    scene_1.printAndWait(resolvePath("/home/kanishk/RESEARCH- MASTERS_LAB/Static_mixer Simulations/Kenics_mixer/KM_1_element_half_inch/Final Simulations/Final_TM_sim_Mesh3/Data Post Processing/Y normal/TM1_re_12000_KM_1_ele_k_ep_Tdr.png"), 1, 1280, 720, true, true);

    simulation_0.getPartManager().removeObjects(planeSection_2);

    simulation_0.getTransformManager().removeObjects(simpleTransform_0);

    simulation_0.getTransformManager().removeObjects(simpleTransform_1);

    simulation_0.getSceneManager().deleteScenes(new NeoObjectVector(new Object[] {scene_1}));
  }
}
