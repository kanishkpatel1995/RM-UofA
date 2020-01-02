import matplotlib.pyplot as plt

import numpy as np 
import pandas as pd
import os 
from matplotlib import rc

D = 12.7e-3

surf_avg_TM1_all_two_layer_SFS = np.zeros([311,400])
nodal_avg_TM1_all_two_layer_SFS = np.zeros([311,400])
A = ["TM1_all_two_layer","TM2_all_two_layer","TM3_all_two_layer"]

for ii in range(3):
    os.chdir(A[ii])
    for cs in range(1,311):
        os.chdir("CrossSection"+str(cs))
        for time in range(1,401):
            surf_avg = pd.read_csv("PS_Area_Surf_Avg_time_"+str(time)+".csv")
            surf_avg_TM1_all_two_layer_SFS[cs-1,time-1] = np.sum(surf_avg['Secondary Fluid Scalar'] * 
                                          surf_avg['Area[k] (m^2)']) / np.sum(surf_avg['Area[k] (m^2)'])
            nodal_avg_TM1_all_two_layer_SFS[cs-1,time-1] = np.mean(surf_avg['Secondary Fluid Scalar'])
            print(cs, time)
        os.chdir("..")
    os.chdir("..")
    np.save(A[ii]+"_surf_avg", surf_avg_TM1_all_two_layer_SFS)
    
    
    
    
