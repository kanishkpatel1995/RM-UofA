#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Dec 10 17:08:12 2019

@author: kanishk
"""

import numpy as np
import matplotlib.pyplot as plt
from mpl_toolkits import mplot3d
from scipy.interpolate import griddata
import matplotlib
matplotlib.rc('font', size=32)

D = 12.7e-3

A_m_TM1 = np.load('A_m_TM1.npy')
A_m_TM2 = np.load('A_m_TM2_all_two_layer.npy')
A_m_TM3 = np.load('A_m_TM3_all_two_layer.npy')



l_m_TM1 = np.load('l_m_TM1.npy')
l_m_TM2 = np.load('l_m_TM2_all_two_layer.npy')
l_m_TM3 = np.load('l_m_TM3_all_two_layer.npy')




lambda_TM1 = np.load('lambda_TM1_all_two_layer.npy')
lambda_TM2 = np.load('lambda_TM2_all_two_layer.npy')
lambda_TM3 = np.load('lambda_TM3_all_two_layer.npy')

surf_avg_TM1 = np.load("TM1_all_two_layer_surf_avg.npy")
surf_avg_TM2 = np.load("TM2_all_two_layer_surf_avg.npy")
surf_avg_TM3 = np.load("TM3_all_two_layer_surf_avg.npy")

z = np.linspace(-0.14,+0.17,311)
t = np.linspace(0,4,400)

T,Z = np.meshgrid(t, z)

plt.figure()
plt.plot(z[0:-2]/D,lambda_TM1[0:-2,-1],'k:',label = r'Realizable k-epsilon')
plt.plot(z[0:-2]/D,lambda_TM2[0:-2,-1], 'k-.',label = 'EB k-epsilon')
plt.plot(z[0:-2]/D,lambda_TM3[0:-2,-1], 'k-',label = 'RSM')
plt.ylabel(r'$\lambda$', fontsize = 32)
plt.ticklabel_format(axis='y', style='sci', scilimits=(0,0))
plt.tick_params(axis = 'both', which = 'major' ,labelsize = 32)
plt.xlabel(r'$z/D$',fontsize = 32)
plt.legend(fontsize = 32, frameon = False)


plt.figure()
plt.plot(z[0:-2]/D,A_m_TM1[0:-2,-1],'k:',label = r'Realizable k-epsilon')
plt.plot(z[0:-2]/D,A_m_TM2[0:-2,-1], 'k-.',label = 'EB k-epsilon')
plt.plot(z[0:-2]/D,A_m_TM3[0:-2,-1], 'k-',label = 'RSM')
plt.ylabel(r'Segregated Area ($A_{m}$)', fontsize = 32)
plt.tick_params(axis = 'both', which = 'major' ,labelsize = 32)
plt.ticklabel_format(style='sci', axis='y', scilimits=(0,0))
plt.xlabel(r'$z/D$',fontsize = 32)
plt.legend(fontsize = 32, frameon = False)

plt.figure()
plt.plot(z[0:-2]/D,l_m_TM1[0:-2,-1],'k:',label = r'Realizable k-epsilon')
plt.plot(z[0:-2]/D,l_m_TM2[0:-2,-1], 'k-.',label = 'EB k-epsilon')
plt.plot(z[0:-2]/D,l_m_TM3[0:-2,-1], 'k-',label = 'RSM')
plt.ylabel(r'Mixing length scales ($l_{m}$)', fontsize = 32)
plt.tick_params(axis = 'both', which = 'major' ,labelsize = 32)
plt.ticklabel_format(style='sci', axis='y', scilimits=(0,0))
plt.xlabel(r'$z/D$',fontsize = 32)
plt.legend(fontsize = 32, frameon = False)


################### 3D plots with surface average as colour map ###############
# fourth dimention - colormap
# create colormap according to x-value (can use any 50x50 array)
resolution = 5
color_dimension = surf_avg_TM1[0:-1:5,0:-1:resolution] # change to desired fourth dimension
minn, maxx = color_dimension.min(), color_dimension.max()
norm = matplotlib.colors.Normalize(minn, maxx)
m = plt.cm.ScalarMappable(norm=norm, cmap='jet')
m.set_array([])
fcolors = m.to_rgba(color_dimension)

# plot
fig = plt.figure()
ax = fig.gca(projection='3d')
reshape_try = np.reshape(lambda_TM1, (124400,1))
# =============================================================================
Zi = np.linspace(-0.14,+0.17,400)
Ti = np.linspace(0,4,400)
li = griddata((Z,T), reshape_try, (Zi,Ti), method='nearest')
# =============================================================================
ax.plot_surface(T[0:-1:5,0:-1:resolution],Z[0:-1:5,0:-1:resolution],lambda_TM1[0:-1:5,0:-1:resolution],
                rstride=1, cstride=1, facecolors=fcolors, vmin=minn, vmax=maxx, shade=True, antialiased=True)
ax.set_xlabel('Time')
ax.set_ylabel('Z')
ax.set_zlabel(r'$\lambda$')
fig.colorbar(m)
#fig.canvas.show()

color_dimension = surf_avg_TM2[0:-1:5,0:-1:10] # change to desired fourth dimension
minn, maxx = color_dimension.min(), color_dimension.max()
norm = matplotlib.colors.Normalize(minn, maxx)
m = plt.cm.ScalarMappable(norm=norm, cmap='jet')
m.set_array([])
fcolors = m.to_rgba(color_dimension)

# plot
ax = fig.gca(projection='3d')
ax.plot_surface(T[0:-1:5,0:-1:10],Z[0:-1:5,0:-1:10],lambda_TM2[0:-1:5,0:-1:10],
                rstride=1, cstride=1, facecolors=fcolors, vmin=minn, vmax=maxx, shade=True, antialiased=True)
ax.set_xlabel('Time')
ax.set_ylabel('Z')
ax.set_zlabel(r'$\lambda$')
fig.canvas.show()



####################### CURVE FITTING #########################################



















