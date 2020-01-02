import matplotlib.pyplot as plt

import numpy as np 
import pandas as pd
import os 
from matplotlib import rc

D = 12.7e-3

rc('font', **{'family': 'serif', 'serif': ['Computer Modern']})
rc('text', usetex=True)

CoV_mesh_1 = []
for i in range(140,266):
    os.chdir('TM 1')
    CoV_1 = pd.read_csv('TM1_all_two_layer_realizable_k_ep_cov'+ str(i) +'.csv')
    c = np.mean(CoV_1['Secondary Fluid Scalar'])
    k = (np.std(CoV_1['Secondary Fluid Scalar']))/c
    CoV_mesh_1 = np.append(CoV_mesh_1, k)
    os.chdir('..')
    
CoV_mesh_2 = []
for i in range(140,266):
    os.chdir('TM 2')
    CoV_1 = pd.read_csv('EB_k_ep_cov_TM2_'+ str(i) +'.csv')
    c = np.mean(CoV_1['Secondary Fluid Scalar'])
    k = (np.std(CoV_1['Secondary Fluid Scalar']))/c
    CoV_mesh_2 = np.append(CoV_mesh_2, k)
    os.chdir('..')
    
    
CoV_mesh_3 = []
for i in range(140,266):
    os.chdir('TM 3')
    CoV_1 = pd.read_csv('all_two_layer_RSM_cov'+ str(i) +'.csv')
    c = np.mean(CoV_1['Secondary Fluid Scalar'])
    k = (np.std(CoV_1['Secondary Fluid Scalar']))/c
    CoV_mesh_3 = np.append(CoV_mesh_3, k)
    os.chdir('..')
    
    
z = (np.linspace(0,0.1265,len(CoV_mesh_1)))/D


plt.figure()
plt.plot(z,CoV_mesh_1,'k:',label = r'Realizable k-epsilon')
plt.plot(z,CoV_mesh_2,'k-.',label = r'EB k-epsilon', markevery = 5)
plt.plot(z,CoV_mesh_3,'k-',label = r'RSM')
plt.ylabel(r'Coefficient of variation', fontsize = 32)
plt.tick_params(axis = 'both', which = 'major' ,labelsize = 32)
plt.xlim(0,10)
plt.ylim(0,9)
plt.xlabel(r'$z/D$',fontsize = 32)
plt.legend(fontsize = 32, frameon = False)













