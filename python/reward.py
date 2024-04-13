import matplotlib
matplotlib.use('TkAgg')  # Ensure compatibility with GUI environments
import matplotlib.pyplot as plt
import numpy as np

# Adjust font sizes for better readability
plt.rcParams['font.size'] = 26
plt.rcParams['axes.unicode_minus'] = False
plt.rc('font',family='Times New Roman')

# Data preparation
years = ["2020", "2021", "2022", "2023"]
provincial_awards = np.array([72, 82, 176, 236])
national_awards = np.array([37, 52, 89, 133])
x = np.arange(len(years))

# Plotting
plt.figure(figsize=(12, 9))
bar_width = 0.35

# Colors for each bar
color1 = (25/255, 144/255, 206/255)  # Yellow for Provincial Awards
color2 = (247/255, 202/255, 71/255)   # Red for National Awards

# Plot bars
rects1 = plt.bar(x - bar_width/2, provincial_awards, bar_width, label='Provincial Awards', color=color1)
rects2 = plt.bar(x + bar_width/2, national_awards, bar_width, label='National Awards', color=color2)

# Adjust y-axis limit
plt.ylim(0, max(provincial_awards.max(), national_awards.max()) + 50)

# Add text for labels, title, and custom x-axis tick labels, etc.
plt.xlabel('Year')
plt.ylabel('Number of Awards')
plt.xticks(x, years)
plt.legend()

# Function to add labels on top of the bars
def add_labels(rects):
    for rect in rects:
        height = rect.get_height()
        plt.annotate('{}'.format(height),
                     xy=(rect.get_x() + rect.get_width() / 2, height),
                     xytext=(0, 3),  # 3 points vertical offset
                     textcoords="offset points",
                     ha='center', va='bottom',
                     fontsize=26)

# Call the function to add labels
add_labels(rects1)
add_labels(rects2)

plt.tight_layout()
plt.savefig('./reward.png')  # Save the figure with updated color scheme
plt.show()
