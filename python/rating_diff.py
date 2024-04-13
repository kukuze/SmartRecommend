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

# Calculating mean values
mean_used_recommendation_system_scores = np.mean(used_recommendation_system_scores)
mean_less_than_10_problems_scores = np.mean(less_than_10_problems_scores)

# Plot adjustments for displaying mean values
plt.figure(figsize=(12, 9)) # Adjusted figure size for better readability
data_to_plot = [used_recommendation_system_scores, less_than_10_problems_scores]

# Increase font sizes globally
plt.rcParams.update({'font.size': 24})
plt.rc('font',family='Times New Roman')
violin_parts = plt.violinplot(data_to_plot, showmeans=True, showmedians=False)
# Colors
color1 = (25/255, 144/255, 206/255)  # Blue
color2 = (247/255, 202/255, 71/255)  # Yellow

# Applying colors
for partname in ('cbars','cmins','cmaxes','cmeans'):
    vp = violin_parts[partname]
    vp.set_edgecolor([color1, color2])
    vp.set_linewidth(2)

for pc, color in zip(violin_parts['bodies'], [color1, color2]):
    pc.set_facecolor(color)
    pc.set_edgecolor(color)
    pc.set_alpha(1)

# Customizing the plot with larger font sizes
plt.xticks([1, 2], ['Used Recommendation System', 'Less than 10 Recommended Problems'], fontsize=26)
plt.ylabel('Score Increase', fontsize=26)

# Annotating mean values with larger font sizes
plt.text(1, mean_used_recommendation_system_scores + 20, f'Mean: {mean_used_recommendation_system_scores:.2f}', ha='center', fontsize=26)
plt.text(2, mean_less_than_10_problems_scores + 20, f'Mean: {mean_less_than_10_problems_scores:.2f}', ha='center', fontsize=26)
plt.tight_layout()
# Show plot
plt.savefig('./rating_diff.png')  # Save the figure with larger text
plt.show()
