// STAR-CCM+ macro: post_processing_data_saving.java
// Written by STAR-CCM+ 14.04.013
package macro;



import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.vis.*;

public class TM1_all_two_layer_post_processing extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {
    double k, l ;
    k = 0.14;
    l = 0.0;

    Simulation simulation_0 = 
      getActiveSimulation();

      /* Saving Velocity Along Centerline */

    XYPlot xYPlot_0 = 
      ((XYPlot) simulation_0.getPlotManager().getPlot("Velocity Along Centerline"));

    xYPlot_0.export(resolvePath("/home/kanishk/RESEARCH- MASTERS_LAB/Static_mixer Simulations/Kenics_mixer/KM_1_element_half_inch/Final Simulations/Final_TM_sim_Mesh3/Data Post Processing/Velocity_along_centerline/velocity_along_centerline_k_ep_TM1.csv"), ",");

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


   /* Creating csv files for plotting data in python of velocity at centerline*/

    XYPlot xYPlot_1 = 
      simulation_0.getPlotManager().createPlot(XYPlot.class);

    xYPlot_1.getParts().setQuery(null);

    xYPlot_1.getParts().setObjects(planeSection_0);

    AxisType axisType_0 = 
      xYPlot_1.getXAxisType();

    axisType_0.getDirectionVector().setComponents(0.0, 1.0, 0.0);

    YAxisType yAxisType_0 = 
      ((YAxisType) xYPlot_1.getYAxes().getAxisType("Y Type 1"));

    FieldFunctionUnits fieldFunctionUnits_0 = 
      yAxisType_0.getScalarFunction();

    PrimitiveFieldFunction primitiveFieldFunction_0 = 
      ((PrimitiveFieldFunction) simulation_0.getFieldFunctionManager().getFunction("Velocity"));

    VectorComponentFieldFunction vectorComponentFieldFunction_0 = 
      ((VectorComponentFieldFunction) primitiveFieldFunction_0.getComponentFunction(2));

    VectorMagnitudeFieldFunction vectorMagnitudeFieldFunction_0 = 
      ((VectorMagnitudeFieldFunction) primitiveFieldFunction_0.getMagnitudeFunction());

    fieldFunctionUnits_0.setFieldFunction(vectorComponentFieldFunction_0);

    InternalDataSet internalDataSet_0 = 
      ((InternalDataSet) yAxisType_0.getDataSetManager().getDataSet("Plane Section"));

    internalDataSet_0.setNeedsSorting(true);

    yAxisType_0.setSmooth(true);


    for (int i = 1; i <= 265; ++i) 
      { 
        planeSection_0.getOriginCoordinate().setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {0.0, 0.0, k})); 
        xYPlot_1.export(resolvePath("/home/kanishk/RESEARCH- MASTERS_LAB/Static_mixer Simulations/Kenics_mixer/KM_1_element_half_inch/Final Simulations/Final_TM_sim_Mesh3/Data Post Processing/Radial Velocity/TM 1_all_two_layer/centerline_"+ Integer.toString(i)+".csv"), ",");
        k = k - 0.001;     
      }

      simulation_0.getPartManager().removeObjects(planeSection_0);

      simulation_0.getPlotManager().deletePlots(new NeoObjectVector(new Object[] {xYPlot_1}));

    

  /* Pressure along centerline */  

    XYPlot xYPlot_2 = 
      ((XYPlot) simulation_0.getPlotManager().getPlot("Pressure Along Centerline"));

    xYPlot_2.export(resolvePath("/home/kanishk/RESEARCH- MASTERS_LAB/Static_mixer Simulations/Kenics_mixer/KM_1_element_half_inch/Final Simulations/Final_TM_sim_Mesh3/Data Post Processing/Pressure_along centerline/Pressure_along_centerline_k_ep_all_two_layer.csv"), ",");




    XYPlot xYPlot_4 = 
      ((XYPlot) simulation_0.getPlotManager().getPlot("Wall Shear stress"));


    xYPlot_4.export(resolvePath("/home/kanishk/RESEARCH- MASTERS_LAB/Static_mixer Simulations/Kenics_mixer/KM_1_element_half_inch/Final Simulations/Final_TM_sim_Mesh3/Data Post Processing/Wall Shear Stress/k_ep_all_two_layer_wall_shear_stress.csv"), ",");


    MonitorPlot monitorPlot_1 = 
      ((MonitorPlot) simulation_0.getPlotManager().getPlot("SurfaceIntegralOfRTDboundaryScalarFlux Monitor Plot"));

    monitorPlot_1.export(resolvePath("/home/kanishk/RESEARCH- MASTERS_LAB/Static_mixer Simulations/Kenics_mixer/KM_1_element_half_inch/Final Simulations/Final_TM_sim_Mesh3/Data Post Processing/RTD/k_ep_all_two_layer_RTD_PSNear_KM.csv"), ",");

    MonitorPlot monitorPlot_3 = 
      ((MonitorPlot) simulation_0.getPlotManager().getPlot("SurfaceIntegralOfSecondaryFluidScalarMidInletExtrusionRTD Monitor Plot"));

    monitorPlot_3.export(resolvePath("/home/kanishk/RESEARCH- MASTERS_LAB/Static_mixer Simulations/Kenics_mixer/KM_1_element_half_inch/Final Simulations/Final_TM_sim_Mesh3/Data Post Processing/RTD/k_ep_all_two_layer_RTD_PS_mid_inlet_extrusion.csv"), ",");

    MonitorPlot monitorPlot_4 = 
      ((MonitorPlot) simulation_0.getPlotManager().getPlot("SurfaceIntegralOfSecondaryFluidScalarRTDCloseToInlet Monitor Plot"));

    monitorPlot_4.export(resolvePath("/home/kanishk/RESEARCH- MASTERS_LAB/Static_mixer Simulations/Kenics_mixer/KM_1_element_half_inch/Final Simulations/Final_TM_sim_Mesh3/Data Post Processing/RTD/k_ep_all_two_layer_RTD_PS_close_to_inlet.csv"), ",");

  }
}
