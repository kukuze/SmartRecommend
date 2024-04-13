import matplotlib
import numpy as np

# Ensure compatibility with GUI environments by setting the backend
matplotlib.use('TkAgg')
import matplotlib.pyplot as plt

# Data
used_recommendation_system_scores = [
    141, 622, 235, 389, 202, 287, -137, 207, 209, 438, 182, 331, 136, 272, 165, -20,
    334, 236, -162, -16, 70, 289, 164, 304, 28, 134, 157, -70, 12, 98, 147, 517, 98,
    249, -43, 418, 100, 39, 165, 102, 150, 370, 232, 294, 230, 347, 363, 729, 260,
    693, 112, 138, 266, 82, 257, 153
]
less_than_10_problems_scores = [
    53, 181, -51, 79, 234, 369, -5, 118, 266, 423, 196, 203, 93, 73, 246, 275, 72,
    146, 584, 436, 175, -11, 225, 229, 63, -4, -177, -56, 28, 115, -167, 128, -2,
    -172, 165, 384, -75, -47, 292, -171, 193, -81, 222, 171, 494, 596, 117, 156, 162,
    404, 49, 20, 234, -91, 431, 224, 179, -199, 111, 216, 168, 25, 254, -54, 342, 78,
    76, 693, 282, 285, 290
]

# Plot adjustments
plt.figure(figsize=(12, 9))  # Adjust figure size for better readability
data_to_plot = [used_recommendation_system_scores, less_than_10_problems_scores]

# Increase font sizes globally for better readability
plt.rcParams.update({'font.size': 26})
plt.rc('font', family='Times New Roman')

# Creating the boxplot with adjusted positions to separate x-axis labels
positions = [1, 2.5]  # Increase the distance between the two boxplots
box = plt.boxplot(data_to_plot, patch_artist=True, showmeans=True, positions=positions, meanline=False, notch=True)

# Colors for the boxplots
colors = [(25/255, 144/255, 206/255),  # Blue
          (247/255, 202/255, 71/255)]  # Yellow

for patch, color in zip(box['boxes'], colors):
    patch.set_facecolor(color)

# Set median lines and mean markers to black
for median in box['medians']:
    median.set_color('black')
for mean in box['means']:
    mean.set_marker('^')
    mean.set_markerfacecolor('black')
    mean.set_markeredgecolor('black')

# Customizing the plot
plt.xticks([1, 2.5], ['Used Recommendation System', 'Less than 10 Recommended Problems'], fontsize=26)
plt.ylabel('Score Increase', fontsize=26)
mean_used_recommendation_system_scores = np.mean(used_recommendation_system_scores)
mean_less_than_10_problems_scores = np.mean(less_than_10_problems_scores)
plt.text(1, mean_used_recommendation_system_scores + 20, f'Mean: {mean_used_recommendation_system_scores:.2f}', ha='center', fontsize=26)
plt.text(2.5, mean_less_than_10_problems_scores + 20, f'Mean: {mean_less_than_10_problems_scores:.2f}', ha='center', fontsize=26)
# Show plot
plt.savefig('./rating_diff_box.png')  # Save the figure with larger text
plt.show()
