// STAR-CCM+ macro: CoV_at_final_time_step.java
// Written by STAR-CCM+ 14.04.013
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.vis.*;

public class TM1_CoV_at_final_time_step extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    
    double k ;
    k = 0.14;


    Simulation simulation_0 = 
      getActiveSimulation();

    PlaneSection planeSection_58 = 
      (PlaneSection) simulation_0.getPartManager().createImplicitPart(new NeoObjectVector(new Object[] {}), new DoubleVector(new double[] {0.0, 0.0, 1.0}), new DoubleVector(new double[] {0.0, 0.0, 0.0}), 0, 1, new DoubleVector(new double[] {0.0}));

    Units units_0 = 
      ((Units) simulation_0.getUnitsManager().getObject("m"));

    planeSection_58.getOriginCoordinate().setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {0.0, 0.0, 0.14}));

    planeSection_58.getInputParts().setQuery(null);

    Region region_0 = 
      simulation_0.getRegionManager().getRegion("Region");

    planeSection_58.getInputParts().setObjects(region_0);

    XyzInternalTable xyzInternalTable_2 = 
      simulation_0.getTableManager().createTable(XyzInternalTable.class);

    PrimitiveFieldFunction primitiveFieldFunction_0 = 
      ((PrimitiveFieldFunction) simulation_0.getFieldFunctionManager().getFunction("Secondary Fluid Scalar"));

    xyzInternalTable_2.setFieldFunctions(new NeoObjectVector(new Object[] {primitiveFieldFunction_0}));

    xyzInternalTable_2.getParts().setQuery(null);

    xyzInternalTable_2.getParts().setObjects(planeSection_58);


    for (int i = 1; i <= 265; ++i)
    {
    

    planeSection_58.getOriginCoordinate().setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {0.0, 0.0, k})); 
    
    xyzInternalTable_2.extract();

    xyzInternalTable_2.export("/home/kanishk/RESEARCH- MASTERS_LAB/Static_mixer Simulations/Kenics_mixer/KM_1_element_half_inch/Final Simulations/Final_TM_sim_Mesh3/Data Post Processing/CoV/TM 1/TM1_all_two_layer_realizable_k_ep_cov" + Integer.toString(i)+".csv", ",");

    k = k - 0.001; 
  
	}
    

    simulation_0.getTableManager().remove(xyzInternalTable_2);

    simulation_0.getPartManager().removeObjects(planeSection_58);


  }
}
