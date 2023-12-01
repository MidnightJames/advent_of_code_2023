#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define BUFFER_SIZE 256
#define FILENAME_SIZE 32
#define FILE_NOT_FOUND -1
#define NO_FILENAME -2
#define NO_NUMBERS_IN_LINE -3

void OpenFile(FILE **file, char *filename)
{
	*file = fopen(filename, "r");

	if (*file == NULL)
	{
		printf("File not found\n");
		exit(FILE_NOT_FOUND);
	}
}

void GetFileName(char* filename)
{
	printf("Enter a filename: ");
	fgets(filename, FILENAME_SIZE, stdin);

	if (!strcmp(filename, ""))
	{
		printf("No filename entered\n");
		exit(NO_FILENAME);
	}

	filename[strcspn(filename, "\n")] = 0;
}

int GetNumberFromLine(char *line)
{
	int firstNumber = 0;
	int secondNumber = 0;
	int numberBuilderIndex = 0;
	char *numbers[] = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
	char numberBuilder[32] = {};

	// Loop through all characters in the string
	for (int i = 0; i < BUFFER_SIZE; ++i)
	{
		// Break if we reach the end of the line
		if (line[i] == '\0')
		{
			break;
		}

		// Check if we have a non-0 digit
		if (isdigit(line[i]))
		{
			// Check if this is the first number we encountered
			if (firstNumber == 0)
			{
				firstNumber = line[i] - '0';
				secondNumber = firstNumber;
			}
			// Otherwise just set the second number
			else
			{
				secondNumber = line[i] - '0';
			}

			// Resetting number builder
			numberBuilderIndex = 0;
			memset(numberBuilder, 0, sizeof(numberBuilder));
		}
		else
		{
			// Add char to string
			numberBuilder[numberBuilderIndex] = line[i];
			++numberBuilderIndex;
			// printf("Current String: %s\n", numberBuilder);

			// Check to see if it's a number
			for (int j = 0; j < 9; ++j)
			{
				// Start at beginning of stringbuilder and move character over every loop
				for (int k = 0; k < (numberBuilderIndex + 1); ++k)
				{
					char slicedNumberBuilder[32] = {};
					strncpy(slicedNumberBuilder, &numberBuilder[k], (numberBuilderIndex + 1 - k));
					// If we have a match
					if (strcmp(slicedNumberBuilder, numbers[j]) == 0)
					{
						// Check if this is the first number we encountered
						if (firstNumber == 0)
						{
							firstNumber = j + 1;
							secondNumber = firstNumber;
						}
						// Otherwise just set the second number
						else
						{
							secondNumber = j + 1;
						}

						// Resetting number builder
						// Also I am saving the last character to place in the first index for stupid edge cases
						char lastChar = numberBuilder[numberBuilderIndex - 1];
						numberBuilderIndex = 1;
						memset(numberBuilder, 0, sizeof(numberBuilder));
						numberBuilder[0] = lastChar;
					}
				}
			}
		}
	}

	// Check if no numbers were entered
	if (firstNumber == '0')
	{
		printf("No numbers found\n");
		exit(NO_NUMBERS_IN_LINE);
	}

	int toReturn = (firstNumber * 10) + secondNumber;
	printf("Returning: %d\n", toReturn);

	return (firstNumber * 10) + secondNumber;
}

int main(int argc, char *argv[])
{
	// Variables
	FILE *file;
	char filename[FILENAME_SIZE] = {};
	char line[BUFFER_SIZE] = {};
	int sum = 0;

	// Check for filename on launch
	if (argc != 1)
	{
		strcpy(filename, argv[1]);
	}
	// Otherwise get filename from user
	else
	{
		GetFileName(filename);
	}

	// Open file
	OpenFile(&file, filename);

	// Loop through file getting every line
	while (fgets(line, sizeof(line), file))
	{
		// Stripping new line
		line[strcspn(line, "\n")] = 0;
		// Get the first and last numbers from the line
		sum += GetNumberFromLine(line);
	}

	printf("Sum of all = %d\n", sum);

	// Cleaning up
	fclose(file);

	return 0;
}