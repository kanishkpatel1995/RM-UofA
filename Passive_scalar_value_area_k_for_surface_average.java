// STAR-CCM+ macro: Passive_scalar_value_area_k_for_surface_average.java
// Written by STAR-CCM+ 14.04.013
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.vis.*;
import star.post.*;

public class Passive_scalar_value_area_k_for_surface_average extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

      double k;

      k = 0.14;

    SolutionHistory solutionHistory_0 = 
      ((SolutionHistory) simulation_0.get(SolutionHistoryManager.class).getObject("Parameters"));

    RecordedSolutionView recordedSolutionView_1 = 
      solutionHistory_0.createRecordedSolutionView(true);

    recordedSolutionView_1.setStateName("State 100");

    SolutionRepresentation solutionRepresentation_1 = 
      ((SolutionRepresentation) simulation_0.getRepresentationManager().getObject("Parameters"));



    PlaneSection planeSection_22 = 
      (PlaneSection) simulation_0.getPartManager().createImplicitPart(new NeoObjectVector(new Object[] {}), new DoubleVector(new double[] {0.0, 0.0, 1.0}), new DoubleVector(new double[] {0.0, 0.0, 0.0}), 0, 1, new DoubleVector(new double[] {0.0}));

    Units units_0 = 
      ((Units) simulation_0.getUnitsManager().getObject("m"));

    planeSection_22.getOriginCoordinate().setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {0.0, 0.0, 0.14}));

    planeSection_22.getInputParts().setQuery(null);

    Region region_0 = 
      simulation_0.getRegionManager().getRegion("Region");

    planeSection_22.getInputParts().setObjects(region_0);





    XyzInternalTable xyzInternalTable_3 = 
      simulation_0.getTableManager().createTable(XyzInternalTable.class);

    PrimitiveFieldFunction primitiveFieldFunction_1 = 
      ((PrimitiveFieldFunction) simulation_0.getFieldFunctionManager().getFunction("passive Scalar Pulse For RTD"));

    PrimitiveFieldFunction primitiveFieldFunction_2 = 
      ((PrimitiveFieldFunction) simulation_0.getFieldFunctionManager().getFunction("Passive Scalar Pulse For RTD Close to Inlet"));

    PrimitiveFieldFunction primitiveFieldFunction_3 = 
      ((PrimitiveFieldFunction) simulation_0.getFieldFunctionManager().getFunction("Passive Scalar Pulse For RTD Mid Inlet Extrusion"));

    PrimitiveFieldFunction primitiveFieldFunction_0 = 
      ((PrimitiveFieldFunction) simulation_0.getFieldFunctionManager().getFunction("Secondary Fluid Scalar"));

    PrimitiveFieldFunction primitiveFieldFunction_4 = 
      ((PrimitiveFieldFunction) simulation_0.getFieldFunctionManager().getFunction("Secondary Fluid Scalar Close to inlet"));

    PrimitiveFieldFunction primitiveFieldFunction_5 = 
      ((PrimitiveFieldFunction) simulation_0.getFieldFunctionManager().getFunction("Secondary Fluid Scalar Mid Inlet Extrusion"));

    PrimitiveFieldFunction primitiveFieldFunction_6 = 
      ((PrimitiveFieldFunction) simulation_0.getFieldFunctionManager().getFunction("Area"));

    VectorComponentFieldFunction vectorComponentFieldFunction_2 = 
      ((VectorComponentFieldFunction) primitiveFieldFunction_6.getComponentFunction(2));

    xyzInternalTable_3.setFieldFunctions(new NeoObjectVector(new Object[] {primitiveFieldFunction_1, primitiveFieldFunction_2, primitiveFieldFunction_3, primitiveFieldFunction_0, primitiveFieldFunction_4, primitiveFieldFunction_5, vectorComponentFieldFunction_2}));

    xyzInternalTable_3.getParts().setObjects(planeSection_22);

     


    for (int time = 1; time <= 400; ++time)

    {

    	recordedSolutionView_1.setStateName("State " + Integer.toString(time));	
    	k = 0.14;
    	

    	for (int i = 0; i <= 310; ++i) 
    	{

    		planeSection_22.getOriginCoordinate().setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {0.0, 0.0, k}));

    		xyzInternalTable_3.setRepresentation(solutionRepresentation_1);

    		xyzInternalTable_3.extract();    

    		xyzInternalTable_3.export("/home/kanishk/RESEARCH- MASTERS_LAB/Static_mixer Simulations/Kenics_mixer/KM_1_element_half_inch/Final Simulations/Final_TM_sim_Mesh3/Data Post Processing/PS_values_Surface_Average/TM1_all_two_layer/CrossSection"+Integer.toString(i)+"/PS_Area_Surf_Avg_time_"+ Integer.toString(time)  + ".csv", ",");
    		k = k - 0.001;

    	}

    }

    /* Remove unnessary planes and scalars from simulation */

    simulation_0.get(SolutionViewManager.class).removeSolutionViews(new NeoObjectVector(new Object[] {recordedSolutionView_1}));

    simulation_0.getPartManager().removeObjects(planeSection_22);

    simulation_0.getTableManager().remove(xyzInternalTable_3);
  }
}
